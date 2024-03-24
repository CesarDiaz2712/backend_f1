package com.cesardiaz.backend.f1.backendf1.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cesardiaz.backend.f1.backendf1.services.TeamService;
import com.google.common.base.Preconditions;

import java.util.Map;

import org.springframework.http.ResponseEntity;
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
    
}
