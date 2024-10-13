package com.cesardiaz.backend.f1.backendf1.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cesardiaz.backend.f1.backendf1.core.advice.ApiResponseError;
import com.cesardiaz.backend.f1.backendf1.dtos.ResetPasswordData;
import com.cesardiaz.backend.f1.backendf1.dtos.UserAppDTO;
import com.cesardiaz.backend.f1.backendf1.services.UserService;
import com.cesardiaz.backend.f1.backendf1.utils.ResponseEntityCustom;
import com.google.common.base.Preconditions;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public UserAppDTO post(@RequestBody(required = true) UserAppDTO userAppDTO) {

        Preconditions.checkNotNull(userAppDTO);

        return userService.createUser(userAppDTO);
        
    }
    
    @SuppressWarnings("static-access")
    @PostMapping("/user/{userId}/resetpassword")
    @PostAuthorize("hasAuthority('ROLE_ADMIN')")
	@Operation(summary = "Create new user", description = "This endpoint creates a new user. If does not exist")
    public ResponseEntity<ResponseEntityCustom> resetPassword(@RequestBody(required = true) ResetPasswordData resetPasswordData, @PathVariable(value = "userId", required = true) Long userId) {

        Preconditions.checkNotNull(resetPasswordData);
        userService.resetPassword(userId, resetPasswordData);;
        return ResponseEntityCustom.getResponseEntity("Contraseña cambiada", HttpStatus.CREATED).ok().build();
        
    }

    
    @PutMapping("/user/{userId}")
	@Operation(summary = "Create new user", description = "This endpoint creates a new user. If does not exist")
    public UserAppDTO put(@RequestBody(required = true) UserAppDTO userAppDTO, @PathVariable(value = "userId", required = true) Long userId) {

        Preconditions.checkNotNull(userAppDTO);
        Preconditions.checkNotNull(userId);

        userAppDTO.setId(userId);
        return userService.updateUser(userAppDTO);
        
    }

    @GetMapping("/user/{userId}")
	@Operation(summary = "Get a user by id", description = "This endpoint find a user by id.")
    public UserAppDTO get(@PathVariable(value = "userId", required = true) Long userId) {

        Preconditions.checkNotNull(userId);

        return  this.userService.findUserById(userId);
    }
    
}
