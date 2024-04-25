package com.cesardiaz.backend.f1.backendf1.services;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.cesardiaz.backend.f1.backendf1.dtos.TeamDTO;
import com.cesardiaz.backend.f1.backendf1.projections.TeamView;
import com.cesardiaz.backend.f1.backendf1.utils.TeamCommandEnum;

public interface TeamService {

    ResponseEntity<String> createTeamRace(Map<String, String> requestMap);

    ResponseEntity<TeamDTO> getTeamById(Long teamId);

    ResponseEntity<Page<TeamView>> getTeams(TeamCommandEnum command, int page, int size);
}
