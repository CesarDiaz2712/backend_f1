package com.cesardiaz.backend.f1.backendf1.controllers;

import com.cesardiaz.backend.f1.backendf1.requests.UserAppRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cesardiaz.backend.f1.backendf1.dtos.ResetPasswordData;
import com.cesardiaz.backend.f1.backendf1.dtos.UserAppDTO;
import com.cesardiaz.backend.f1.backendf1.services.UserService;
import com.cesardiaz.backend.f1.backendf1.utils.ResponseEntityCustom;
import com.google.common.base.Preconditions;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * REST controller that exposes endpoints for managing application users.
 * <p>
 * All endpoints are secured with role-based access control using
 * Spring Security's {@link PreAuthorize}. Base path: {@code /api}.
 * </p>
 */
@RestController
@Tag(name = "User", description = "Module developed where can manages user's functions.")
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * Creates a new user in the system.
     * <p>
     * Requires the request body to be non-null. The username must not
     * already exist. Accessible only to users with {@code ROLE_SUPERADMIN}
     * or {@code CREATE_USER} authority.
     * </p>
     *
     * <p><b>POST</b> {@code /api/user}</p>
     *
     * @param userAppRequest object containing the data for the new user
     * @return {@link UserAppDTO} with the created user's information
     * @throws NullPointerException      if {@code userAppRequest} is null
     */
    @PostMapping("/user")
	@Operation(summary = "Create new user", description = "This endpoint creates a new user. If does not exist")
    @Parameter(name = "UserAppDTO", description = "Set the attributes required to create a new client.")
    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN') or hasAuthority('CREATE_USER')")
    public UserAppDTO post(@RequestBody(required = true) UserAppRequest userAppRequest) {

        Preconditions.checkNotNull(userAppRequest);

        return userService.createUser(userAppRequest);
        
    }

    /**
     * Resets the password of a specific user.
     * <p>
     * Validates that the old password matches the current one and that
     * the new password is different. Saves an audit record of the change.
     * Accessible only to users with {@code ROLE_SUPERADMIN}
     * or {@code CHANGE_PASSWORD} authority.
     * </p>
     *
     * <p><b>POST</b> {@code /api/user/{userId}/resetpassword}</p>
     *
     * @param resetPasswordData object containing the old and new passwords
     * @param userId            ID of the user whose password will be reset
     * @return {@link ResponseEntity} with a confirmation message and HTTP 201 status
     * @throws NullPointerException      if {@code resetPasswordData} is null
     */
    @SuppressWarnings("static-access")
    @PostMapping("/user/{userId}/resetpassword")
    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN') or hasAuthority('CHANGE_PASSWORD')")
	@Operation(summary = "Reset password user", description = "Functionality where reset user's password and sendging a notification.")
    @Parameter(name = "UserId", description = "Param using to search and update info user.")
    @Parameter(name = "ResetPasswordData", description = "Into this object set the information password.")
    public ResponseEntity<ResponseEntityCustom> resetPassword(@RequestBody(required = true) ResetPasswordData resetPasswordData, @PathVariable(value = "userId", required = true) Long userId) {

        Preconditions.checkNotNull(resetPasswordData);
        userService.resetPassword(userId, resetPasswordData);
        return ResponseEntityCustom.getResponseEntity("Contraseña cambiada", HttpStatus.CREATED).ok().build();
        
    }

    /**
     * Updates the information of an existing user.
     * <p>
     * Both the request body and the user ID must be non-null. The new username
     * must not be already taken by another user. Accessible only to users with
     * {@code ROLE_SUPERADMIN} or {@code UPDATE_USER} authority.
     * </p>
     *
     * <p><b>PUT</b> {@code /api/user/{userId}}</p>
     *
     * @param userId         ID of the user to update
     * @return {@link UserAppDTO} with the updated user's information
     * @throws NullPointerException      if {@code userAppRequest} or {@code userId} is null
     */
    @PutMapping("/user/{userId}")
	@Operation(summary = "Update user", description = "This endpoint updates some information user. Needs a param as userId and a object where containts info.")
    @Parameter(name = "UserId", description = "Param using to search and update info user.")
    @Parameter(name = "UserAppDTO", description = "Into this object set the information to change.")
    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN') or hasAuthority('UPDATE_USER')")
    public UserAppDTO put(@RequestBody(required = true) UserAppRequest userAppDTO, @PathVariable(value = "userId", required = true) Long userId) {

        Preconditions.checkNotNull(userAppDTO);
        Preconditions.checkNotNull(userId);

        return userService.updateUser(userAppDTO, userId);
        
    }

    /**
     * Retrieves a user by their ID.
     * <p>
     * Accessible only to users with {@code ROLE_SUPERADMIN}
     * or {@code GET_USER} authority.
     * </p>
     *
     * <p><b>GET</b> {@code /api/user/{userId}}</p>
     *
     * @param userId ID of the user to retrieve
     * @return {@link UserAppDTO} with the found user's information
     * @throws NullPointerException      if {@code userId} is null
     */
    @GetMapping("/user/{userId}")
	@Operation(summary = "Get a user by id", description = "This endpoint find a user by id.")
    @Parameter(name = "userId", description = "User Id to search info user")
    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN') or  hasAuthority('GET_USER')")
    public UserAppDTO get(@PathVariable(value = "userId", required = true) Long userId) {

        Preconditions.checkNotNull(userId);

        return  this.userService.findUserById(userId);
    }
    
    
}
