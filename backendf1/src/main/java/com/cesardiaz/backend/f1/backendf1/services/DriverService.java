package com.cesardiaz.backend.f1.backendf1.services;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.cesardiaz.backend.f1.backendf1.dtos.DriverDTO;
import com.cesardiaz.backend.f1.backendf1.projections.DriverDataView;


public interface DriverService {

    ResponseEntity<?> createNewDriver(Map<String, String> requestMap);
    
    ResponseEntity<?> getDriver(Long id);
    DriverDTO getDriverDTO(Long id);
    
    ResponseEntity<Page<DriverDataView>> getAllDrivers(String command, int page, int size);
}
