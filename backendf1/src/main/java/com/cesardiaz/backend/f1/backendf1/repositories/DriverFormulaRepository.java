package com.cesardiaz.backend.f1.backendf1.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cesardiaz.backend.f1.backendf1.models.DriverFormulaOne;

@Repository
public interface DriverFormulaRepository extends JpaRepository<DriverFormulaOne, Long>{

    Optional<DriverFormulaOne> findByGamertag(@Param("gamertag") String gamertag);
}
