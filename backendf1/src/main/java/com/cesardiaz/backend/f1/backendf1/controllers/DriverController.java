package com.cesardiaz.backend.f1.backendf1.controllers;

import com.cesardiaz.backend.f1.backendf1.core.advice.BadRequestCustomException;
import com.cesardiaz.backend.f1.backendf1.core.advice.ResourceNotFoundException;
import com.cesardiaz.backend.f1.backendf1.core.advice.UnsopportedParamsException;
import com.cesardiaz.backend.f1.backendf1.dtos.DriverDTO;
import com.cesardiaz.backend.f1.backendf1.requests.DriverRequest;
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

/**
 * REST controller that exposes endpoints for managing Formula One drivers.
 * <p>
 * Provides operations to create, retrieve, and list F1 drivers.
 * Base path: {@code /api}.
 * </p>
 */
@RestController
@Tag(name = "Driver", description = "Service where you can manages diferents kind of services as CRUD of a F1 Driver.")
@RequestMapping("/api")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService){
        this.driverService = driverService;

    }

    /**
     * Creates a new Formula One driver.
     * <p>
     * Validates required fields, checks for duplicate gamertag and full name,
     * and verifies that the associated user is not already linked to another driver.
     * </p>
     *
     * <p><b>POST</b> {@code /api/driver}</p>
     *
     * @param driverRequest object containing the new driver's data
     * @return HTTP 200 with the created {@link DriverDTO}
     * @throws UnsopportedParamsException if any required field is missing or blank
     * @throws BadRequestCustomException  if the gamertag, full name, or userId is already in use
     * @throws ResourceNotFoundException  if the associated user does not exist
     */
    @PostMapping("/driver")
    @Operation(summary = "Create a new F1 Driver.", description = "This endpoint save a new driver if does not exist.")
    public ResponseEntity<DriverDTO> post(@RequestBody(required = true) DriverRequest driverRequest) {

        return ResponseEntity.ok(driverService.createNewDriver(driverRequest));
    }

    /**
     * Retrieves a Formula One driver by their ID.
     *
     * <p><b>GET</b> {@code /api/driver/{id}}</p>
     *
     * @param driverId the ID of the driver to retrieve
     * @return HTTP 200 with the found {@link DriverDTO}
     * @throws NullPointerException      if {@code driverId} is null
     * @throws ResourceNotFoundException if no driver is found with the given ID
     */
    @GetMapping("/driver/{id}")
    @Operation(summary = "Find a F1 driver by id.", description = "This endpoint gets a driver registrated by id, if exist.")
    public ResponseEntity<DriverDTO> get( @PathVariable(name = "id") Long driverId) {

        Preconditions.checkNotNull(driverId);

        return ResponseEntity.ok(driverService.getDriver(driverId));
    }

    /**
     * Retrieves a paginated list of Formula One drivers filtered by command.
     * <p>
     * Supported commands are defined in {@link DriverCommandEnum}:
     * <ul>
     *   <li>{@code actual_drivers} — returns only active drivers</li>
     *   <li>{@code all_drivers} — returns all drivers regardless of status</li>
     * </ul>
     * </p>
     *
     * <p><b>GET</b> {@code /api/drivers}</p>
     *
     * @param command filter command to determine which drivers to retrieve
     *                (defaults to {@code actual_drivers})
     * @param page    zero-based page index (defaults to {@code 0})
     * @param size    number of records per page (defaults to {@code 20})
     * @return HTTP 200 with a {@link Page} of {@link DriverDataView} projections
     * @throws NullPointerException      if {@code command} is null
     * @throws BadRequestCustomException if no results are found for the given command
     */
    @GetMapping("/drivers")
    @Operation(summary = "Returns a Page object by f1 driver.", description = "This endpoint returns a Page object that contains a DriverDataView object with information about each driver.")
    public ResponseEntity<Page<DriverDataView>> getAll(
			@RequestParam(name = "command", defaultValue = "actual_drivers") DriverCommandEnum command,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "20") int size ) {

        Preconditions.checkNotNull(command);

        return ResponseEntity.ok(driverService.getAllDrivers(command.getCode(), page, size));
    }
    
}
