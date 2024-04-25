package com.cesardiaz.backend.f1.backendf1.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesardiaz.backend.f1.backendf1.constans.MessageCustom;
import com.cesardiaz.backend.f1.backendf1.models.ContractDriver;
import com.cesardiaz.backend.f1.backendf1.models.ContractDriverKey;
import com.cesardiaz.backend.f1.backendf1.models.DriverFormulaOne;
import com.cesardiaz.backend.f1.backendf1.models.TeamFormulaOne;
import com.cesardiaz.backend.f1.backendf1.repositories.ContractDriverRepository;
import com.cesardiaz.backend.f1.backendf1.utils.ResponseEntityCustom;
import com.cesardiaz.backend.f1.backendf1.utils.validation.ContractValidationRequest;

@Service
public class ContractDriverServiceImp implements ContractDriverService{

    private final ContractValidationRequest contractValidationRequest;
    private final ContractDriverRepository  contractDriverRepository;

    public ContractDriverServiceImp(ContractValidationRequest contractValidationRequest, ContractDriverRepository contractDriverRepository){
        this.contractValidationRequest = contractValidationRequest;
        this.contractDriverRepository = contractDriverRepository;

    }

    @Override
    public ResponseEntity<String> assignContractToDriver(Map<String, String> requestMap, Long driverId) {
        // TODO Auto-generated method stub

        if(requestMap!=null && contractValidationRequest.validateParamsToCreate(requestMap) && driverId != null){
            ContractDriver contractDriver = getContractDriverFromRequestMap(requestMap, driverId);

            if(contractDriver==null){
                return ResponseEntity.internalServerError().build();
            }

            List<ContractDriver> contractDrivers = contractDriverRepository.findAllContractByTeamActivated();

            if(contractDrivers.size()>=2){
                return ResponseEntity.internalServerError().build();
            }

            ContractDriverKey key = new ContractDriverKey( driverId, contractDriver.getTeam().getId());
            Optional<ContractDriver> optionalcontract = contractDriverRepository.findContractDriverByContractDriverKeyAndStatus(key,true);
 
            if(optionalcontract.isPresent()){
                return ResponseEntityCustom.getResponseEntity(MessageCustom.DATA_ALREADY_CREATED, HttpStatus.CONFLICT);
            }

            contractDriverRepository.save(contractDriver);

            return ResponseEntityCustom.getResponseEntity(MessageCustom.SUCCESS, HttpStatus.CREATED);
        }

        return ResponseEntity.badRequest().build();
    }

    private ContractDriver getContractDriverFromRequestMap(Map<String, String> requestMap, Long driverid){
        if(requestMap!=null){
            try {
                    
                Long teamId = Long.parseLong(requestMap.get("teamId"));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                Date initialDateContract = formatter.parse(requestMap.get("initialContractDate"));
                Date finalContractDate = formatter.parse(requestMap.get("finalContractDate"));

                return  ContractDriver.instance(new DriverFormulaOne(driverid), new TeamFormulaOne(teamId), initialDateContract, finalContractDate, new Date(), null);
            } catch (ParseException e) {
                // TODO: handle exception
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<String> resignContractOfDriver(Long driverId, Long teamId) {
        // TODO Auto-generated method stub

        if(driverId != null && teamId != null){

            ContractDriverKey key = new ContractDriverKey( driverId, teamId);
            Optional<ContractDriver> optionalContract = contractDriverRepository.findContractDriverByContractDriverKeyAndStatus(key,true);
            
            if(optionalContract.isPresent()){
                ContractDriver contractDriver = optionalContract.get();
                contractDriver.setDateUpdated(new Date());
                contractDriver.setStatus(false);
                return ResponseEntity.ok().build();
            }else{
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.internalServerError().build();
    }

}
