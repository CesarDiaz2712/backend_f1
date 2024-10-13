package com.cesardiaz.backend.f1.backendf1.utils.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cesardiaz.backend.f1.backendf1.core.advice.UnsopportedParamsException;
import com.cesardiaz.backend.f1.backendf1.dtos.ResetPasswordData;
import com.cesardiaz.backend.f1.backendf1.dtos.UserAppDTO;

@Component
public class UserAppValidationRequest {

    public boolean validateIdMap(Map<String, String> requestMap){

        if(requestMap.containsKey("id")){
            return true;
        }else
        return false;
    }

    public void validateParamsToCreateUser(UserAppDTO userAppDTO){

        List<String> params = new ArrayList<>();

        if(userAppDTO.getFirstname() == null){
            params.add("firstname");
        }
        
        if(userAppDTO.getLastname() == null){
            params.add("lastname");
        }
        if(userAppDTO.getUsername() == null){
            params.add("username");
        }
        if(userAppDTO.getPassword() == null){
            params.add("password");
        }

        if (params.size()>0) {
            throw new UnsopportedParamsException(params);
        }

    }

    public void validateParamsToResetPassword(ResetPasswordData resetPasswordData){

        List<String> params = new ArrayList<>();

        if(resetPasswordData.getNewPassword() == null){
            params.add("newPassword");
        }
        
        if(resetPasswordData.getOldPassword() == null){
            params.add("oldPassword");
        }
        if (params.size()>0) {
            throw new UnsopportedParamsException(params);
        }
    }
}
