package com.cesardiaz.backend.f1.backendf1.services;

import com.cesardiaz.backend.f1.backendf1.dtos.ResetPasswordData;
import com.cesardiaz.backend.f1.backendf1.dtos.UserAppDTO;

public interface UserService {
    
    UserAppDTO updateUser(UserAppDTO requestMap);

    UserAppDTO findUserById(Long id);

    UserAppDTO findUserByUsernamePassword(String username);

    UserAppDTO createUser(UserAppDTO userAppDTO);

    void resetPassword(Long userId, ResetPasswordData resetPasswordData);
}
