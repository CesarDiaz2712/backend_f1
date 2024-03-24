package com.cesardiaz.backend.f1.backendf1.services;

import java.util.Map;

import org.springframework.http.ResponseEntity;


public interface DriverService {

    ResponseEntity<?> createNewDriver(Map<String, String> requestMap);
    
    ResponseEntity<?> getDriver(Long id);
    
    ResponseEntity<?> getAllDrivers(String command);
}
