package com.cesardiaz.backend.f1.backendf1.services;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface TeamService {

    ResponseEntity<String> createTeamRace(Map<String, String> requestMap);
}
