package com.cesardiaz.backend.f1.backendf1.services;

import java.util.Date;
import java.util.Optional;

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
import com.cesardiaz.backend.f1.backendf1.models.UserApp;
import com.cesardiaz.backend.f1.backendf1.repositories.ResetPasswordRepository;
import com.cesardiaz.backend.f1.backendf1.repositories.RoleRepository;
import com.cesardiaz.backend.f1.backendf1.repositories.UserRepository;
import com.cesardiaz.backend.f1.backendf1.utils.validation.UserAppValidationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverterDto userConverterDto;
    private final PasswordEncoder passwordEncoder;
    private final UserAppValidationRequest appValidationForm;
    private final RoleRepository roleRepository;
    private final ResetPasswordRepository resetPasswordRepository;

    public UserServiceImpl(UserRepository userRepository, UserConverterDto userConverterDto,
            PasswordEncoder passwordEncoder, UserAppValidationRequest appValidationForm,
            RoleRepository roleRepository, ResetPasswordRepository resetPasswordRepository) {
        this.userRepository = userRepository;
        this.userConverterDto = userConverterDto;
        this.passwordEncoder = passwordEncoder;
        this.appValidationForm = appValidationForm;
        this.roleRepository = roleRepository;
        this.resetPasswordRepository = resetPasswordRepository;
    }

    @Override
    @Transactional
    public UserAppDTO updateUser(UserAppDTO userAppDTO) {

        if (userAppDTO.getId() == null)
            throw new NullPointerException();

        Optional<UserApp> userOptional = userRepository.findById(userAppDTO.getId());

        if (!userOptional.isPresent()) {
            throw new ResourceNotFoundException(ErrorKeyEnum.NOT_FOUND);
        }

        UserApp userApp = userOptional.get();

        return userConverterDto.convertEntityToDto(userApp.updateInfoUser(userAppDTO));

    }

    @Override
    public UserAppDTO findUserById(Long id) {

        if (id == null) {
            throw new NullPointerException();
        }

        Optional<UserApp> userOptional = this.userRepository.findById(id);

        if (!userOptional.isPresent())
            throw new ResourceNotFoundException(ErrorKeyEnum.NOT_FOUND);

        return this.userConverterDto.convertEntityToDto(userOptional.get());

    }

    @Override
    public UserAppDTO createUser(UserAppDTO userAppDTO) {

        appValidationForm.validateParamsToCreateUser(userAppDTO);
        Optional<UserApp> userAppOptinal = userRepository.findByUsername(userAppDTO.getUsername());

        if (!userAppOptinal.isPresent()) {
            UserApp user = userConverterDto.convertDtoToEntity(userAppDTO);
            user.setPassword(passwordEncoder.encode(userAppDTO.getPassword()));

            user.setDateCreated(new Date());

            userRepository.save(user);

            return userConverterDto.convertEntityToDto(user);
        } else {
            throw new BadRequestCustomException(ErrorKeyEnum.DATA_DUPLICATED, null);
        }

    }

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

        ObjectMapper mapper = new ObjectMapper();

        DetailsUserData detailsUserData = mapper.convertValue(authentication.getDetails(), DetailsUserData.class);

        ResetPasswordEntity resetPasswordEntity = new ResetPasswordEntity(detailsUserData.getUserId(), new Date(), userApp, oldPasswordEncode,
                newPasswordEncode);
        resetPasswordRepository.save(resetPasswordEntity);

        // sendNotification to email.
    }

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
