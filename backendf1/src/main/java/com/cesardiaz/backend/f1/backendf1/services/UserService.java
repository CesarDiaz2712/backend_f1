package com.cesardiaz.backend.f1.backendf1.services;

import com.cesardiaz.backend.f1.backendf1.dtos.ResetPasswordData;
import com.cesardiaz.backend.f1.backendf1.dtos.UserAppDTO;
import com.cesardiaz.backend.f1.backendf1.requests.UserAppRequest;

public interface UserService {
    
    UserAppDTO updateUser(UserAppRequest userAppRequest, Long userId);

    UserAppDTO findUserById(Long id);

    UserAppDTO findUserByUsernamePassword(String username);

    UserAppDTO createUser(UserAppRequest userAppRequest);

    void resetPassword(Long userId, ResetPasswordData resetPasswordData);
}
