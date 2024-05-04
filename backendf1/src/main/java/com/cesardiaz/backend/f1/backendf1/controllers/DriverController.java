package com.cesardiaz.backend.f1.backendf1.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cesardiaz.backend.f1.backendf1.projections.DriverDataView;
import com.cesardiaz.backend.f1.backendf1.services.DriverService;
import com.cesardiaz.backend.f1.backendf1.utils.DriverCommandEnum;
import com.google.common.base.Preconditions;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@Tag(name = "Driver", description = "Service where you can manages diferents kind of services as CRUD of a F1 Driver.")
@RequestMapping("/api")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService){
        this.driverService = driverService;

    }

    @PostMapping("/driver")
    @Operation(summary = "Create a new F1 Driver.", description = "This endpoint save a new driver if does not exist.")
    public ResponseEntity<?> post(@RequestBody(required = true) Map<String, String> requestMap) {
        //TODO: process POST request
        
        Preconditions.checkNotNull(requestMap);

        return driverService.createNewDriver(requestMap);
    }

    
    @GetMapping("/driver/{id}")
    @Operation(summary = "Find a F1 driver by id.", description = "This endpoint gets a driver registrated by id, if exist.")
    public ResponseEntity<?> get( @PathVariable(name = "id") Long driverId) {

        Preconditions.checkNotNull(driverId);

        return driverService.getDriver(driverId);
    }

    @GetMapping("/drivers")
    @Operation(summary = "Returns a Page object by f1 driver.", description = "This endpoint returns a Page object that contains a DriverDataView object with information about each driver.")
    public ResponseEntity<Page<DriverDataView>> getAll(
			@RequestParam(name = "command", defaultValue = "actual_drivers") Optional<DriverCommandEnum> command,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "20") int size ) {

        return driverService.getAllDrivers(command.get().getCode(), page, size);
    }
    
}
