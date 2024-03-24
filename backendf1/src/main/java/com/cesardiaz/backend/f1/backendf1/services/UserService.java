package com.cesardiaz.backend.f1.backendf1.services;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cesardiaz.backend.f1.backendf1.dtos.UserAppDTO;

public interface UserService {

    UserAppDTO createUser(UserAppDTO userAppDTO);
    
    ResponseEntity<?> updateUser(Map<String,String> requestMap);

    ResponseEntity<UserAppDTO> findUserById(Long id);

    ResponseEntity<String> signUp(Map<String, String> requestMap);
}
