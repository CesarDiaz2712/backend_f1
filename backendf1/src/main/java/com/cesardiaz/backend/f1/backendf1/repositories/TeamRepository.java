package com.cesardiaz.backend.f1.backendf1.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cesardiaz.backend.f1.backendf1.models.TeamFormulaOne;
import com.cesardiaz.backend.f1.backendf1.projections.TeamView;

@Repository
public interface TeamRepository extends JpaRepository<TeamFormulaOne, Long>{

    @Query("SELECT t FROM TeamFormulaOne t WHERE t.name LIKE %:name%")
    Optional<TeamFormulaOne> findByName(@Param("name") String name);

    @Query(value = "SELECT t.id as id, t.name as name, t.alias as alias FROM team t WHERE t.is_active= :isActive", nativeQuery = true)
    Page<TeamView> findAllTeams(Pageable pageable, boolean isActive);
}
