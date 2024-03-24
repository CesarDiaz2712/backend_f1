package com.cesardiaz.backend.f1.backendf1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cesardiaz.backend.f1.backendf1.models.ContractDriver;
import com.cesardiaz.backend.f1.backendf1.models.ContractDriverKey;

public interface ContractDriverRepository extends JpaRepository<ContractDriver, ContractDriverKey>{
    
}
