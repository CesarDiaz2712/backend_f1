package com.cesardiaz.backend.f1.backendf1.utils.validation;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class TeamValidationRequest {

    public boolean validateParamsToCreate(Map<String, String> requestMap){

        if(requestMap.containsKey("teamId") && requestMap.containsKey("alias")){
            return true;
        }else
        return false;
    }
}
