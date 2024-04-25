package com.cesardiaz.backend.f1.backendf1.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cesardiaz.backend.f1.backendf1.dtos.TeamDTO;
import com.cesardiaz.backend.f1.backendf1.projections.TeamView;
import com.cesardiaz.backend.f1.backendf1.services.TeamService;
import com.cesardiaz.backend.f1.backendf1.utils.TeamCommandEnum;
import com.google.common.base.Preconditions;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/api")
@RestController
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService){
        this.teamService = teamService;

    }

    @PostMapping("/team")
    public ResponseEntity<String> post(@RequestBody Map<String, String> requestMap) {
        //TODO: process POST request
        
        Preconditions.checkNotNull(requestMap);

        return teamService.createTeamRace(requestMap);
    }

    

    @PostMapping("/team/{id}")
    public ResponseEntity<TeamDTO> get(@PathVariable(name = "id") Long teamId) {
        //TODO: process POST request
        
        Preconditions.checkNotNull(teamId);

        return teamService.getTeamById(teamId);
    }

    
    @PostMapping("/teams")
    public ResponseEntity<Page<TeamView>> getAll(
        @RequestParam(name = "command", defaultValue = "actual_teams") Optional<TeamCommandEnum> command,
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "10") int size ) {
        //TODO: process POST request
        
        Preconditions.checkNotNull(command);
        Preconditions.checkNotNull(page);
        Preconditions.checkNotNull(size);

        return teamService.getTeams(command.get(), page, size);
    }
    
}
