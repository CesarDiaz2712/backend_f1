package com.cesardiaz.backend.f1.backendf1.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cesardiaz.backend.f1.backendf1.services.ContractDriverService;
import com.google.common.base.Preconditions;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class ContractDriverController {

    private final ContractDriverService contractDriverService;

    public ContractDriverController(ContractDriverService contractDriverService){
        this.contractDriverService = contractDriverService;
        
    }

    @PostMapping("/contractdriver/{id}")
    public ResponseEntity<String> post(@RequestBody(required = true) Map<String, String> requestMap, @PathVariable(name = "id", required = true) Long driverId) {
        //TODO: process POST request
        
        Preconditions.checkNotNull(driverId);
        Preconditions.checkNotNull(requestMap);

        return contractDriverService.assignContractToDriver(requestMap, driverId);
    }

    @PutMapping("/contractdriver/{id}")
    public ResponseEntity<String> put(@RequestParam( name = "teamId",required = true) Long teamId, @PathVariable(name = "id", required = true) Long driverId) {
        //TODO: process POST request
        
        Preconditions.checkNotNull(driverId);
        Preconditions.checkNotNull(teamId);

        return contractDriverService.resignContractOfDriver(driverId, teamId);
    }
    
}
