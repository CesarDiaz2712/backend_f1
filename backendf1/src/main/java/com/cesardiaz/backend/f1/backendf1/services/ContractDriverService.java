package com.cesardiaz.backend.f1.backendf1.services;

import org.springframework.http.ResponseEntity;

public interface ContractDriverService {
    ResponseEntity<String> assignContractToDriver();
    
}
