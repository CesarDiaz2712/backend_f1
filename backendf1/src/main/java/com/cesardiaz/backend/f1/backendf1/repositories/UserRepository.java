package com.cesardiaz.backend.f1.backendf1.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cesardiaz.backend.f1.backendf1.models.UserApp;

@Repository
public interface UserRepository extends JpaRepository<UserApp, Long>{

    Optional<UserApp> findByUsername(String username);
    
}
