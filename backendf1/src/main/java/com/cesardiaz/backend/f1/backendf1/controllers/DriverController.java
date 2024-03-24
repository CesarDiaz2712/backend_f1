package com.cesardiaz.backend.f1.backendf1.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cesardiaz.backend.f1.backendf1.dtos.DriverDTO;
import com.cesardiaz.backend.f1.backendf1.services.DriverService;
import com.cesardiaz.backend.f1.backendf1.utils.DriverCommandEnum;
import com.google.common.base.Preconditions;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService){
        this.driverService = driverService;

    }

    @PostMapping("/driver")
    public ResponseEntity<?> post(@RequestBody(required = true) Map<String, String> requestMap) {
        //TODO: process POST request
        
        Preconditions.checkNotNull(requestMap);

        return driverService.createNewDriver(requestMap);
    }

    
    @GetMapping("/driver/{id}")
    public ResponseEntity<?> get( @PathVariable(name = "id") Long driverId) {

        Preconditions.checkNotNull(driverId);

        return driverService.getDriver(driverId);
    }

    @GetMapping("/drivers")
    public ResponseEntity<Page<DriverDTO>> getAll(
			@RequestParam(name = "command", defaultValue = "actual_drivers") Optional<DriverCommandEnum> command,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "20") int size ) {

        
                

        return null;
    }
    
}
