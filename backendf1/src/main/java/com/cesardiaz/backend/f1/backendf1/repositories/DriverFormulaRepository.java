package com.cesardiaz.backend.f1.backendf1.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cesardiaz.backend.f1.backendf1.models.DriverFormulaOne;
import com.cesardiaz.backend.f1.backendf1.projections.DriverDataView;

@Repository
public interface DriverFormulaRepository extends JpaRepository<DriverFormulaOne, Long> {

    Optional<DriverFormulaOne> findByGamertag(@Param("gamertag") String gamertag);

    @Query(value = "select dr.id as driverId, dr.name as name, dr.lastname as lastname, dr.gamertag as gamertag, dr.number_driver as numberDriver, dr.date_created as dateCreated, dr.user_id as userId, cd.date_initial_contract as initialDateContract, cd.date_end_contract as finalDateContract, cd.status as status FROM f1_driver dr "
            + "left join contract_driver cd on dr.id = cd.race_driver_id "
            + " WHERE cd.status = 1 ", nativeQuery = true)
    Page<DriverDataView> findAllDriversByStatusActivated(Pageable pageable);

    @Query(value = "select dr.id as id, dr.name as name, dr.lastname as lastname, dr.gamertag as gamertag, dr.number_driver as numberDriver, dr.date_created as dateCreated, dr.user_id as userId FROM f1_driver dr ", nativeQuery = true)
    Page<DriverDataView> findAllDrivers(Pageable pageable);
}
