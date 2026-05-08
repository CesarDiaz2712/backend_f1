package com.cesardiaz.backend.f1.backendf1.utils.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cesardiaz.backend.f1.backendf1.requests.UserAppRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.cesardiaz.backend.f1.backendf1.core.advice.UnsopportedParamsException;
import com.cesardiaz.backend.f1.backendf1.dtos.ResetPasswordData;
import com.cesardiaz.backend.f1.backendf1.dtos.UserAppDTO;

/**
 * Spring component responsible for validating incoming request parameters
 * for user-related operations.
 * <p>
 * Collects all missing required fields and throws an
 * {@link UnsopportedParamsException} with the full list of violations,
 * allowing the caller to receive all errors in a single response
 * rather than one at a time.
 * </p>
 */
@Component
public class UserAppValidationRequest extends ValidationParamsAbstract{

    /**
     * Validates that all required fields for creating a new user are present.
     * <p>
     * Required fields: {@code firstname}, {@code lastname},
     * {@code username}, and {@code password}.
     * </p>
     *
     * @param userAppDTO the request object containing the new user's data
     * @throws UnsopportedParamsException if one or more required fields are null,
     *                                    containing the names of all missing fields
     */
    public void validateParamsToCreateUser(UserAppRequest userAppDTO){

        List<String> params = new ArrayList<>();

        validateBlank(userAppDTO.getFirstname(), "firstname", params);
        validateBlank(userAppDTO.getLastname(),  "lastname",  params);
        validateBlank(userAppDTO.getUsername(),  "username",  params);
        validateBlank(userAppDTO.getPassword(),  "password",  params);

        if (!params.isEmpty()) {
            throw new UnsopportedParamsException(params);
        }

    }

    /**
     * Validates that all required fields for updating an existing user are present.
     * <p>
     * Required fields: {@code firstname}, {@code lastname}, and {@code username}.
     * Unlike creation, {@code password} is not required for updates.
     * </p>
     *
     * @param userAppDTO the request object containing the updated user's data
     * @throws UnsopportedParamsException if one or more required fields are null,
     *                                    containing the names of all missing fields
     */
    public void validateParamsToUpdateUser(UserAppRequest userAppDTO){

        List<String> params = new ArrayList<>();
        validateBlank(userAppDTO.getFirstname(), "firstname", params);
        validateBlank(userAppDTO.getLastname(),  "lastname",  params);
        validateBlank(userAppDTO.getUsername(),  "username",  params);

        if (!params.isEmpty()) {
            throw new UnsopportedParamsException(params);
        }

    }

    /**
     * Validates that all required fields for resetting a user's password are present.
     * <p>
     * Required fields: {@code newPassword} and {@code oldPassword}.
     * </p>
     *
     * @param resetPasswordData the object containing the old and new passwords
     * @throws UnsopportedParamsException if one or more required fields are null,
     *                                    containing the names of all missing fields
     */
    public void validateParamsToResetPassword(ResetPasswordData resetPasswordData){

        List<String> params = new ArrayList<>();

        validateBlank(resetPasswordData.getNewPassword(),  "newPassword",  params);
        validateBlank(resetPasswordData.getOldPassword(), "oldPassword", params);

        if (!params.isEmpty()) {
            throw new UnsopportedParamsException(params);
        }
    }
}
