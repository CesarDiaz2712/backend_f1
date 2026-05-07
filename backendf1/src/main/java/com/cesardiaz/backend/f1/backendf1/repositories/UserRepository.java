package com.cesardiaz.backend.f1.backendf1.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cesardiaz.backend.f1.backendf1.models.UserApp;

@Repository
public interface UserRepository extends JpaRepository<UserApp, Long>{

    Optional<UserApp> findByUsername(String username);

    @Query(value = "SELECT CASE WHEN count(u.id) > 0 THEN true ELSE false END FROM user_app u where u.username=:username AND u.id != :userId",nativeQuery = true)
    boolean existUsernameDuplicatedByOtherUser(@Param("username") String username, @Param("userId") Long userId);
    
}
