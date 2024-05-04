package com.cesardiaz.backend.f1.backendf1.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cesardiaz.backend.f1.backendf1.dtos.UserAppDTO;
import com.cesardiaz.backend.f1.backendf1.services.UserService;
import com.google.common.base.Preconditions;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@Tag(name = "User", description = "Service where can manages diferents operations as a CRUD of a user.")
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
	@Operation(summary = "Create new user", description = "This endpoint creates a new user. If does not exist")
    public ResponseEntity<String> post(@RequestBody(required = true) Map<String, String> requestMap) {
        //TODO: process POST request
        
        if(requestMap==null){
            return ResponseEntity.badRequest().build();
        }

        ResponseEntity<String> response = userService.signUp(requestMap);
        
        return response;
    }

    @GetMapping("/user/{id}")
	@Operation(summary = "Get a user by id", description = "This endpoint find a user by id.")
    public ResponseEntity<UserAppDTO> get(@PathVariable(value = "id") Optional<Long> id) {
        //TODO: process POST request

        Preconditions.checkNotNull(id);

        if(id.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        return  this.userService.findUserById(id.get());
    }
    
}
