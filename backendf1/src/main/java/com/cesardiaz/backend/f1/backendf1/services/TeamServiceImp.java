package com.cesardiaz.backend.f1.backendf1.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cesardiaz.backend.f1.backendf1.constans.MessageCustom;
import com.cesardiaz.backend.f1.backendf1.models.TeamFormulaOne;
import com.cesardiaz.backend.f1.backendf1.projections.TeamView;
import com.cesardiaz.backend.f1.backendf1.repositories.TeamRepository;
import com.cesardiaz.backend.f1.backendf1.utils.ResponseEntityCustom;
import com.cesardiaz.backend.f1.backendf1.utils.TeamEnum;
import com.cesardiaz.backend.f1.backendf1.utils.validation.TeamValidationRequest;

import jakarta.transaction.Transactional;

@Service
public class TeamServiceImp implements TeamService {

    private final TeamValidationRequest teamValidationRequest;
    private final TeamRepository teamRepository;

    public TeamServiceImp(TeamValidationRequest teamValidationRequest, TeamRepository teamRepository) {
        this.teamValidationRequest = teamValidationRequest;
        this.teamRepository = teamRepository;

    }

    @Override
    @Transactional
    public ResponseEntity<String> createTeamRace(Map<String, String> requestMap) {
        // TODO Auto-generated method stub

        if (teamValidationRequest.validateParamsToCreate(requestMap)) {
            TeamFormulaOne team = getTeamFromMap(requestMap);
            Optional<TeamView> teamExist = teamRepository.findTeamByAproximation(team.getName());

            if(!teamExist.isPresent()){
                team.setDateCreated(new Date());
                teamRepository.save(team);
                return ResponseEntity.ok().build();
            }else{
                return ResponseEntityCustom.getResponseEntity(MessageCustom.DATA_ALREADY_CREATED, HttpStatus.CONFLICT);
            }

        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    private String validateTeamName(Integer nameToCheck) {

        if(nameToCheck.equals(TeamEnum.redbull.getId())){
            return TeamEnum.redbull.getCode();
        }else if(nameToCheck.equals(TeamEnum.mclaren.getId())){
            return TeamEnum.mclaren.getCode();
        }else if(nameToCheck.equals(TeamEnum.mercedes.getId())){
            return TeamEnum.mercedes.getCode();
        }else{
            throw new RuntimeException("Error with team name");
        }
    }

    private TeamFormulaOne  getTeamFromMap(Map<String, String> requestMap){

        try {
                
            if(requestMap!=null){
                
                String teamId = requestMap.get("teamId");
                Integer id = Integer.parseInt(teamId);

                String nameTeam = validateTeamName(id);

                TeamFormulaOne team =  new TeamFormulaOne(teamId!=null?Long.parseLong(teamId):null, nameTeam, requestMap.get("alias"),
                requestMap.get("yearRegister"), requestMap.get("teamChief"), requestMap.get("technicalChief"), null, null);

                return team;
            }
            return null;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw new RuntimeException("Internal Server Error");
        }
    }

}
