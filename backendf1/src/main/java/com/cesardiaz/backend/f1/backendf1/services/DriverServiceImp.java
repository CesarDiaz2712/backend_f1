package com.cesardiaz.backend.f1.backendf1.services;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesardiaz.backend.f1.backendf1.components.DriverConverterDTO;
import com.cesardiaz.backend.f1.backendf1.constans.MessageCustom;
import com.cesardiaz.backend.f1.backendf1.dtos.DriverDTO;
import com.cesardiaz.backend.f1.backendf1.models.DriverFormulaOne;
import com.cesardiaz.backend.f1.backendf1.models.UserApp;
import com.cesardiaz.backend.f1.backendf1.projections.DriverDataView;
import com.cesardiaz.backend.f1.backendf1.repositories.DriverFormulaRepository;
import com.cesardiaz.backend.f1.backendf1.repositories.UserRepository;
import com.cesardiaz.backend.f1.backendf1.utils.ResponseEntityCustom;
import com.cesardiaz.backend.f1.backendf1.utils.validation.DriverValidationRequest;

@Service
public class DriverServiceImp implements DriverService {

    private final DriverValidationRequest driverValidationRequest;
    private final DriverFormulaRepository driverRepository;
    private final UserRepository userRepository;
    private final DriverConverterDTO driverConverterDTO;

    @Autowired
    public DriverServiceImp(DriverValidationRequest driverValidationRequest, DriverFormulaRepository driverRepository,
            UserRepository userRepository, DriverConverterDTO driverConverterDTO) {
        this.driverValidationRequest = driverValidationRequest;
        this.driverRepository = driverRepository;
        this.userRepository = userRepository;
        this.driverConverterDTO = driverConverterDTO;

    }

    @Override
    @Transactional
    public ResponseEntity<?> createNewDriver(Map<String, String> requestMap) {
        // TODO Auto-generated method stub

        try {

            if (driverValidationRequest.validateParamsToCreate(requestMap)) {

                DriverFormulaOne driverFormulaOne = getDriverFormulaFromMap(requestMap);

                Optional<DriverFormulaOne> isFoundDriver = driverRepository
                        .findByGamertag(driverFormulaOne.getGamertag());

                if (!isFoundDriver.isPresent()) {
                    Optional<UserApp> userExist = userRepository.findById(driverFormulaOne.getUser().getId());

                    if (!userExist.isPresent()) {
                        return ResponseEntity.badRequest().build();
                    }
                    driverFormulaOne.setUser(userExist.get());
                    driverRepository.save(driverFormulaOne);
                    return ResponseEntityCustom.getResponseEntityClass(
                            driverConverterDTO.convertEntityToDTO(driverFormulaOne), HttpStatus.CREATED);
                } else {
                    return ResponseEntityCustom.getResponseEntity(MessageCustom.DATA_ALREADY_CREATED,
                            HttpStatus.CONFLICT);
                }

            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw new RuntimeException("Internal server error");
        }

    }

    private DriverFormulaOne getDriverFormulaFromMap(Map<String, String> requestMap) {

        if (requestMap != null) {
            try {

                String driverId = requestMap.get("id");
                String userId = requestMap.get("userId");
                Long driverIdParsed = null;
                Long userIdParsed = null;
                if (driverId != null) {
                    driverIdParsed = Long.parseLong(driverId);
                }

                if (userId != null) {

                    userIdParsed = Long.parseLong(userId);
                }

                String name = requestMap.get("name");
                String lastname = requestMap.get("lastname");
                String gamertag = requestMap.get("gamertag");
                String numberDriver = requestMap.get("numberDriver");

                UserApp userApp = null;
                if (userIdParsed != null) {
                    userApp = new UserApp(userIdParsed);
                }
                return new DriverFormulaOne(driverIdParsed, name, lastname, gamertag, numberDriver, LocalDate.now(),
                        null, userApp);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public ResponseEntity<Page<DriverDataView>> getAllDrivers(String command, int page, int size) {

        switch (command) {
            case "actual_drivers":

                Page<DriverDataView> driversActivated = driverRepository
                        .findAllDriversByStatusActivated(PageRequest.of(page, size, Sort.by("id")));

                if (driversActivated != null) {
                    return ResponseEntity.ok().body(driversActivated);
                } else
                    return ResponseEntity.notFound().build();

            case "all_drivers":

                Page<DriverDataView> drivers = driverRepository
                        .findAllDrivers(PageRequest.of(page, size, Sort.by("id")));

                if (drivers != null) {
                    return ResponseEntity.ok().body(drivers);
                } else
                    return ResponseEntity.notFound().build();

            default:
                return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> getDriver(Long id) {
        // TODO Auto-generated method stub

        if (id != null) {

            Optional<DriverFormulaOne> driverExist = driverRepository.findById(id);

            if (driverExist.isPresent()) {
                return ResponseEntity.ok().body(driverConverterDTO.convertEntityToDTO(driverExist.get()));
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public DriverDTO getDriverDTO(Long id) {
        // TODO Auto-generated method stub
        Optional<DriverFormulaOne> driverExist = driverRepository.findById(id);
        return driverConverterDTO.convertEntityToDTO(driverExist.get());
    }
}
