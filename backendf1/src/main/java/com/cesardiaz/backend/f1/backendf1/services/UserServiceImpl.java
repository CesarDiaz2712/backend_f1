package com.cesardiaz.backend.f1.backendf1.services;

import java.util.Date;
import java.util.Optional;

import com.cesardiaz.backend.f1.backendf1.requests.UserAppRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cesardiaz.backend.f1.backendf1.components.UserConverterDto;
import com.cesardiaz.backend.f1.backendf1.core.advice.BadRequestCustomException;
import com.cesardiaz.backend.f1.backendf1.core.advice.ResourceNotFoundException;
import com.cesardiaz.backend.f1.backendf1.core.constants.ErrorKeyEnum;
import com.cesardiaz.backend.f1.backendf1.dtos.DetailsUserData;
import com.cesardiaz.backend.f1.backendf1.dtos.ResetPasswordData;
import com.cesardiaz.backend.f1.backendf1.dtos.UserAppDTO;
import com.cesardiaz.backend.f1.backendf1.models.ResetPasswordEntity;
import com.cesardiaz.backend.f1.backendf1.models.Role;
import com.cesardiaz.backend.f1.backendf1.models.UserApp;
import com.cesardiaz.backend.f1.backendf1.repositories.ResetPasswordRepository;
import com.cesardiaz.backend.f1.backendf1.repositories.UserRepository;
import com.cesardiaz.backend.f1.backendf1.utils.validation.UserAppValidationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

/**
 * Implementation of {@link UserService} that handles all business logic
 * related to application user management.
 * <p>
 * Provides functionality for creating, updating, and retrieving users,
 * as well as managing password resets with an audit trail.
 * </p>
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverterDto userConverterDto;
    private final PasswordEncoder passwordEncoder;
    private final UserAppValidationRequest appValidationForm;
    private final ResetPasswordRepository resetPasswordRepository;
    private final ObjectMapper mapper;

    public UserServiceImpl(UserRepository userRepository, UserConverterDto userConverterDto,
                           PasswordEncoder passwordEncoder, UserAppValidationRequest appValidationForm, ResetPasswordRepository resetPasswordRepository, ObjectMapper mapper) {
        this.userRepository = userRepository;
        this.userConverterDto = userConverterDto;
        this.passwordEncoder = passwordEncoder;
        this.appValidationForm = appValidationForm;
        this.resetPasswordRepository = resetPasswordRepository;
        this.mapper = mapper;
    }

    /**
     * Updates the information of an existing user.
     * <p>
     * Validates that the ID is not null, that the request parameters are valid,
     * that the user exists, and that the new username is not already taken
     * by a different user.
     * </p>
     *
     * @param userAppRequest object containing the updated user data
     * @param userId         ID of the user to update
     * @return {@link UserAppDTO} with the updated user information
     * @throws NullPointerException      if {@code userId} is null
     * @throws ResourceNotFoundException if no user is found with the given ID
     * @throws BadRequestCustomException if the username is already in use by another user
     */
    @Override
    @Transactional
    public UserAppDTO updateUser(UserAppRequest userAppRequest, Long userId) {

        if (userId == null)
            throw new NullPointerException();

        appValidationForm.validateParamsToUpdateUser(userAppRequest);

        Optional<UserApp> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException(ErrorKeyEnum.NOT_FOUND);
        }

        if (userRepository.existUsernameDuplicatedByOtherUser(userAppRequest.getUsername(), userId))
            throw new BadRequestCustomException(ErrorKeyEnum.BAD_REQUEST_USER_EXIST);

        UserApp userApp = userOptional.get();

        return userConverterDto.convertEntityToDto(userApp.updateInfoUser(userAppRequest));

    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return {@link UserAppDTO} with the found user's information
     * @throws NullPointerException      if {@code id} is null
     * @throws ResourceNotFoundException if no user is found with the given ID
     */
    @Override
    public UserAppDTO findUserById(Long id) {

        if (id == null) {
            throw new NullPointerException();
        }

        Optional<UserApp> userOptional = this.userRepository.findById(id);

        if (userOptional.isEmpty())
            throw new ResourceNotFoundException(ErrorKeyEnum.NOT_FOUND);

        return this.userConverterDto.convertEntityToDto(userOptional.get());

    }

    /**
     * Creates and persists a new user in the system.
     * <p>
     * Validates the input parameters, verifies that the username does not
     * already exist, encodes the password, assigns the user's roles,
     * and saves the new user.
     * </p>
     *
     * @param userAppRequest object containing the new user's data
     * @return {@link UserAppDTO} with the created user's information
     * @throws BadRequestCustomException if the username already exists in the system
     */
    @Override
    public UserAppDTO createUser(UserAppRequest userAppRequest) {

        appValidationForm.validateParamsToCreateUser(userAppRequest);

        Optional<UserApp> userAppOptinal = userRepository.findByUsername(userAppRequest.getUsername());

        if (userAppOptinal.isEmpty()) {
            UserApp user = userConverterDto.convertDtoToEntity(userAppRequest);
            user.setPassword(passwordEncoder.encode(userAppRequest.getPassword()));

            for (Role role : user.getRoles()) {
                user.addNewRole(role);
            }

            user.setDateCreated(new Date());

            userRepository.save(user);

            return userConverterDto.convertEntityToDto(user);
        } else {
            throw new BadRequestCustomException(ErrorKeyEnum.DATA_DUPLICATED, null);
        }

    }

    /**
     * Resets the password of an authenticated user.
     * <p>
     * Verifies that the provided old password matches the current one,
     * ensures the new password is different from the current one, updates
     * the password, and saves an audit record of the change linked to the
     * authenticated user who performed the action.
     * </p>
     * <p>
     * <b>TODO:</b> Send an email notification to the user after a successful reset.
     * </p>
     *
     * @param userId            ID of the user whose password is being reset
     * @param resetPasswordData object containing the old and new passwords
     * @throws NullPointerException      if {@code userId} is null
     * @throws ResourceNotFoundException if no user is found with the given ID
     * @throws BadRequestCustomException if the old password does not match the current one,
     *                                   or if the new password is the same as the current one
     */
    @Override
    @Transactional
    public void resetPassword(Long userId, ResetPasswordData resetPasswordData) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
        if (userId == null)
            throw new NullPointerException();

        appValidationForm.validateParamsToResetPassword(resetPasswordData);

        Optional<UserApp> userOptional = userRepository.findById(userId);

        if (!userOptional.isPresent()) {
            throw new ResourceNotFoundException(ErrorKeyEnum.NOT_FOUND);
        }

        UserApp userApp = userOptional.get();

        if (!passwordEncoder.matches( resetPasswordData.getOldPassword(), userApp.getPassword()))
            throw new BadRequestCustomException(ErrorKeyEnum.RESET_PASSWORD_VALIDATION, null);

        if (passwordEncoder.matches(resetPasswordData.getNewPassword(), userApp.getPassword()))
            throw new BadRequestCustomException(ErrorKeyEnum.RESET_PASSWORD_SIMILAR, null);

        String oldPasswordEncode = passwordEncoder.encode(resetPasswordData.getOldPassword());
        String newPasswordEncode = passwordEncoder.encode(resetPasswordData.getNewPassword());
        userApp.setPassword(newPasswordEncode);

        DetailsUserData detailsUserData = mapper.convertValue(authentication.getDetails(), DetailsUserData.class);

        ResetPasswordEntity resetPasswordEntity = new ResetPasswordEntity(detailsUserData.getUserId(), new Date(), userApp, oldPasswordEncode,
                newPasswordEncode);
        resetPasswordRepository.save(resetPasswordEntity);

        // sendNotification to email.
    }

    /**
     * Retrieves a user by their username.
     *
     * @param username the username to search for
     * @return {@link UserAppDTO} with the found user's information
     * @throws NullPointerException      if {@code username} is null
     * @throws ResourceNotFoundException if no user is found with the given username
     */
    @Override
    public UserAppDTO findUserByUsernamePassword(String username) {
        
        if (username == null) {
            throw new NullPointerException();
        }

        Optional<UserApp> userOptional = this.userRepository.findByUsername(username);

        if (!userOptional.isPresent())
            throw new ResourceNotFoundException(ErrorKeyEnum.NOT_FOUND);

        return this.userConverterDto.convertEntityToDto(userOptional.get());

    }

}
