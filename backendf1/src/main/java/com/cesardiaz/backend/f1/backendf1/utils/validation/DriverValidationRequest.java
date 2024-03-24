package com.cesardiaz.backend.f1.backendf1.utils.validation;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class DriverValidationRequest {

    public boolean validateParamsToCreate(Map<String, String> requestMap){

        if(requestMap.containsKey("name") && requestMap.containsKey("lastname") && requestMap.containsKey("gamertag")
        && requestMap.containsKey("numberDriver")){
            return true;
        }else
        return false;
    }
}
