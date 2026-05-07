package com.cesardiaz.backend.f1.backendf1.utils.validation;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.cesardiaz.backend.f1.backendf1.core.advice.UnsopportedParamsException;
import com.cesardiaz.backend.f1.backendf1.dtos.TeamDTO;

@Component
public class TeamValidationRequest {

    public void validateParamsToCreate(TeamDTO teamDTO){

        List<String> list = new ArrayList<>();
        if(teamDTO.getName()==null){
            list.add("name");
        }
        if(teamDTO.getAlias()==null){
            list.add("alias");
        }
        if(teamDTO.getTeamChief()==null){
            list.add("team chief");
        }
        if(teamDTO.getTechnicalChief()==null){
            list.add("technical chief");
        }
        if(teamDTO.getYearRegister()==null){
            list.add("year register");
        }
        
        if(list.size()>0){
            throw new UnsopportedParamsException(list);
        }
    }
}
