package com.cesardiaz.backend.f1.backendf1.services;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface ContractDriverService {
    ResponseEntity<String> assignContractToDriver(Map<String, String> requestMap, Long driverId);

    ResponseEntity<String> resignContractOfDriver(Long driverId, Long teamId);
    
}
