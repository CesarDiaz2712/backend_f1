package com.cesardiaz.backend.f1.backendf1.services;

import java.util.Map;

import com.cesardiaz.backend.f1.backendf1.requests.DriverRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.cesardiaz.backend.f1.backendf1.dtos.DriverDTO;
import com.cesardiaz.backend.f1.backendf1.projections.DriverDataView;


public interface DriverService {

    DriverDTO createNewDriver(DriverRequest driverRequest);

    DriverDTO getDriver(Long id);
    DriverDTO getDriverDTO(Long id);
    
    Page<DriverDataView> getAllDrivers(String command, int page, int size);
}
