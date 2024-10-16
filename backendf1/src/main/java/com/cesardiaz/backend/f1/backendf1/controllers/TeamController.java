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
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Parameter(name = "TeamDto", description = "Set the attributes required to create a new team.")
    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN') or hasAuthority('CREATE_TEAM')")
    public TeamDTO post(@RequestBody TeamDTO teamDTO) {
        //TODO: process POST request
        
        Preconditions.checkNotNull(teamDTO);

        return teamService.createTeamRace(teamDTO);
    }

    

    @PostMapping("/team/{id}")
    @Operation(summary = "Find a F1 team by id", description = "Search a team's information.")
    @Parameter(name = "teamId", description = "Id allow you to search one specific team.")
    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN') or hasAuthority('GET_TEAM')")
    public TeamDTO get(@PathVariable(name = "id") Long teamId) {
        //TODO: process POST request
        
        Preconditions.checkNotNull(teamId);

        return teamService.getTeamById(teamId);
    }

    
    @PostMapping("/teams")
    @Operation(summary = "Find a F1 teams by Page", description = "Search a pagable of teams with diferents type of searching.")
    @Parameter(name = "command", description = "This param allow you to select an specific type of search as all of teams or the actived teams from this seasson.")
    @Parameter(name = "page", description = "This param is the number of pages.")
    @Parameter(name = "size", description = "This param is the size of elements in a page.")
    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN') or hasAuthority('GET_TEAMS')")
    public Page<TeamView> getAll(
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
