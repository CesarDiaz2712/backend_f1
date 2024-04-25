package com.cesardiaz.backend.f1.backendf1.components;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.cesardiaz.backend.f1.backendf1.dtos.TeamDTO;
import com.cesardiaz.backend.f1.backendf1.models.TeamFormulaOne;

@Component
public class TeamConvertDTO {

    public TeamFormulaOne convertDtoToEntity(TeamDTO teamDTO){
        if(teamDTO!=null){

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date createdDate=null;
            Date updatedDate=null;
            try {
                createdDate = formato.parse(teamDTO.getCreatedDate());
                updatedDate = formato.parse(teamDTO.getUpdatedDate());
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    
            return new TeamFormulaOne(teamDTO.getId(), teamDTO.getName(), teamDTO.getAlias(), teamDTO.getYearRegister(), teamDTO.getTeamChief(), teamDTO.getTechnicalChief(), createdDate, updatedDate);
        }
        return null;
    }

    public TeamDTO convertEntityToDTO(TeamFormulaOne team){
        if(team!=null){
            return new TeamDTO(team.getId(), team.getName(), team.getAlias(), team.getTeamChief(), team.getTechnicalChief(), team.getDateCreated().toString(), team.getYearRegister(), team.getDateUpdated().toString());
        }

        return null;
    }
    
}
