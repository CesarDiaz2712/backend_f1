package com.cesardiaz.backend.f1.backendf1.utils.validation;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ContractValidationRequest {

    public boolean validateParamsToCreate(Map<String, String> requestMap){

        if(requestMap.containsKey("teamId") && requestMap.containsKey("initialContractDate")
        && requestMap.containsKey("finalContractDate")){
            return true;
        }else
        return false;
    }
}
