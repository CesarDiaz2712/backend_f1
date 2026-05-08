package com.cesardiaz.backend.f1.backendf1.services;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import com.cesardiaz.backend.f1.backendf1.core.advice.BadRequestCustomException;
import com.cesardiaz.backend.f1.backendf1.core.advice.ResourceNotFoundException;
import com.cesardiaz.backend.f1.backendf1.core.advice.UnsopportedParamsException;
import com.cesardiaz.backend.f1.backendf1.core.constants.ErrorKeyEnum;
import com.cesardiaz.backend.f1.backendf1.dtos.UserAppDTO;
import com.cesardiaz.backend.f1.backendf1.requests.DriverRequest;
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

/**
 * Implementation of {@link DriverService} that handles the business logic
 * for Formula One driver management.
 * <p>
 * Provides functionality for creating, retrieving, and listing drivers,
 * including pagination and filtering by status.
 * </p>
 */
@Service
public class DriverServiceImp implements DriverService {

    private final DriverValidationRequest driverValidationRequest;
    private final DriverFormulaRepository driverRepository;
    private final DriverConverterDTO driverConverterDTO;
    private final UserService userService;

    public DriverServiceImp(DriverValidationRequest driverValidationRequest, DriverFormulaRepository driverRepository,
                            DriverConverterDTO driverConverterDTO, UserService userService) {
        this.driverValidationRequest = driverValidationRequest;
        this.driverRepository = driverRepository;
        this.driverConverterDTO = driverConverterDTO;

        this.userService = userService;
    }

    /**
     * Creates and persists a new Formula One driver.
     * <p>
     * Performs the following validations before saving:
     * <ul>
     *   <li>Required fields are present and not blank</li>
     *   <li>The associated user exists</li>
     *   <li>The user is not already assigned to another driver</li>
     *   <li>No driver with the same gamertag already exists</li>
     *   <li>No driver with the same full name already exists</li>
     * </ul>
     * </p>
     *
     * @param driverRequest object containing the new driver's data
     * @return {@link DriverDTO} with the persisted driver's information
     * @throws UnsopportedParamsException if any required field is missing or blank
     * @throws ResourceNotFoundException  if the associated user does not exist
     * @throws BadRequestCustomException  if the user is already assigned to a driver,
     *                                    or if a driver with the same gamertag or full name exists
     */
    @Override
    @Transactional
    public DriverDTO createNewDriver(DriverRequest driverRequest) {

        driverValidationRequest.validateParamsToCreate(driverRequest);

        Long userId = driverRequest.getUserId();

        UserAppDTO userAppDTO = userService.findUserById(userId);

        if(driverRepository.userIdAssignedToADriver(userId))
            throw new BadRequestCustomException(ErrorKeyEnum.BAD_REQUEST_USER_DRIVER);

        DriverFormulaOne driverFormulaOne = driverConverterDTO.convertDriverRequestToEntity(driverRequest, userAppDTO);

        Optional<DriverFormulaOne> isFoundDriver = driverRepository
                .findByGamertag(driverFormulaOne.getGamertag());

        if (isFoundDriver.isEmpty() && !driverRepository.existDriverByFullName(driverRequest.getFirstname(), driverRequest.getLastname())) {
            DriverFormulaOne driverFormulaOneSaved = driverRepository.save(driverFormulaOne);
            return driverConverterDTO.convertEntityToDTO(driverFormulaOneSaved);
        }

        throw new BadRequestCustomException(ErrorKeyEnum.BAD_REQUEST_DRIVER_EXIST);

    }

    /**
     * Retrieves a paginated list of drivers filtered by the given command.
     * <p>
     * Supported commands:
     * <ul>
     *   <li>{@code "actual_drivers"} — returns only active/activated drivers</li>
     *   <li>{@code "all_drivers"} — returns all drivers regardless of status</li>
     * </ul>
     * Results are sorted by {@code id} in ascending order.
     * </p>
     *
     * @param command filter command to determine which drivers to retrieve
     * @param page    zero-based page index
     * @param size    number of records per page
     * @return a {@link Page} of {@link DriverDataView} projections
     * @throws BadRequestCustomException if no results are found
     *                                   or the command is unrecognized
     */
    @Override
    public Page<DriverDataView> getAllDrivers(String command, int page, int size) {

        switch (command) {
            case "actual_drivers":

                Page<DriverDataView> driversActivated = driverRepository
                        .findAllDriversByStatusActivated(PageRequest.of(page, size, Sort.by("id")));

                if (driversActivated.isEmpty())
                    throw new BadRequestCustomException(ErrorKeyEnum.BAD_REQUEST);

                return driversActivated;

            case "all_drivers":

                Page<DriverDataView> drivers = driverRepository
                        .findAllDrivers(PageRequest.of(page, size, Sort.by("id")));

                if (drivers.isEmpty())
                    throw new BadRequestCustomException(ErrorKeyEnum.BAD_REQUEST);

                return drivers;

            default:
                throw new BadRequestCustomException(ErrorKeyEnum.BAD_REQUEST);
        }
    }

    /**
     * Retrieves a driver by their ID.
     *
     * @param id the ID of the driver to retrieve
     * @return {@link DriverDTO} with the driver's information
     * @throws BadRequestCustomException if {@code id} is null
     * @throws ResourceNotFoundException if no driver is found with the given ID
     */
    @Override
    public DriverDTO getDriver(Long id) {

        if (id == null)
            throw new BadRequestCustomException(ErrorKeyEnum.BAD_REQUEST);

        DriverFormulaOne driverExist = driverRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(ErrorKeyEnum.NOT_FOUND_DRIVER, "ID", id));

        return driverConverterDTO.convertEntityToDTO(driverExist);
    }

    /**
     * Retrieves a driver by their ID and returns the result as a {@link DriverDTO}.
     * <p>
     * Unlike {@link #getDriver(Long)}, this method does not validate
     * whether {@code id} is null before querying the repository.
     * </p>
     *
     * @param id the ID of the driver to retrieve
     * @return {@link DriverDTO} with the driver's information
     * @throws ResourceNotFoundException if no driver is found with the given ID
     */
    @Override
    public DriverDTO getDriverDTO(Long id) {
        Optional<DriverFormulaOne> driverExist = driverRepository.findById(id);
        return driverExist
                .map(driverConverterDTO::convertEntityToDTO)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorKeyEnum.NOT_FOUND));
    }
}
