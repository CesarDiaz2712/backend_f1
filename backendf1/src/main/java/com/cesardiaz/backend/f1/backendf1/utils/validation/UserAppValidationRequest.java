package com.cesardiaz.backend.f1.backendf1.utils.validation;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class UserAppValidationRequest {

    public boolean validateIdMap(Map<String, String> requestMap){

        if(requestMap.containsKey("id")){
            return true;
        }else
        return false;
    }

    public boolean validateSignUpMap(Map<String, String> requestMap){

        if(requestMap.containsKey("name") && requestMap.containsKey("username") && requestMap.containsKey("password")
        && requestMap.containsKey("role")){
            return true;
        }else
        return false;
    }
}
