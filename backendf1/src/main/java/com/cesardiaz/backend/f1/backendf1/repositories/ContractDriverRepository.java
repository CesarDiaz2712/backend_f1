package com.cesardiaz.backend.f1.backendf1.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cesardiaz.backend.f1.backendf1.models.ContractDriver;
import com.cesardiaz.backend.f1.backendf1.models.ContractDriverKey;

public interface ContractDriverRepository extends JpaRepository<ContractDriver, ContractDriverKey>{
    
    Optional<ContractDriver> findContractDriverByContractDriverKeyAndStatus(@Param("contractDriverKey") ContractDriverKey key, @Param("status") Boolean status);

    @Query(value = "Select cd.race_driver_id, cd.team_id, cd.status, cd.date_initial_contract , cd.date_end_contract , cd.date_created, cd.date_updated  FROM contract_driver cd WHERE cd.status = true ", nativeQuery = true)
    List<ContractDriver> findAllContractByTeamActivated();
}
