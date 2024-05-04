package com.cesardiaz.backend.f1.backendf1.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cesardiaz.backend.f1.backendf1.dtos.TeamDTO;
import com.cesardiaz.backend.f1.backendf1.projections.TeamView;
import com.cesardiaz.backend.f1.backendf1.services.TeamService;
import com.cesardiaz.backend.f1.backendf1.utils.TeamCommandEnum;
import com.google.common.base.Preconditions;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/api")
@Tag(name = "Team", description = "Service where can manages diferents operations as a CRUD of a F1 team.")
@RestController
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService){
        this.teamService = teamService;

    }

    @PostMapping("/team")
    @Operation(summary = "Creat new F1 team", description = "This endpoint creates new f1 teams if doesnt exist.")
    public ResponseEntity<String> post(@RequestBody Map<String, String> requestMap) {
        //TODO: process POST request
        
        Preconditions.checkNotNull(requestMap);

        return teamService.createTeamRace(requestMap);
    }

    

    @PostMapping("/team/{id}")
    @Operation(summary = "Find a F1 team by id", description = "This endpoint gets a team registrated by id, if exist.")
    public ResponseEntity<TeamDTO> get(@PathVariable(name = "id") Long teamId) {
        //TODO: process POST request
        
        Preconditions.checkNotNull(teamId);

        return teamService.getTeamById(teamId);
    }

    
    @PostMapping("/teams")
    @Operation(summary = "Find a F1 teams by Page", description = "This endpoint gets teams with differets types of returns. Depends by command.")
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
