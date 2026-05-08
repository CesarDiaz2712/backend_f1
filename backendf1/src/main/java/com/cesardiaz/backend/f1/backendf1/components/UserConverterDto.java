package com.cesardiaz.backend.f1.backendf1.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.cesardiaz.backend.f1.backendf1.requests.UserAppRequest;
import org.springframework.stereotype.Component;

import com.cesardiaz.backend.f1.backendf1.dtos.RoleDTO;
import com.cesardiaz.backend.f1.backendf1.dtos.UserAppDTO;
import com.cesardiaz.backend.f1.backendf1.models.Role;
import com.cesardiaz.backend.f1.backendf1.models.UserApp;

/**
 * Spring component responsible for converting between {@link UserApp} entity objects
 * and their corresponding DTO representations.
 * <p>
 * This converter is used across the service layer to decouple the internal
 * domain model from the data exposed through the API.
 * </p>
 */
@Component
public class UserConverterDto {

    /**
     * Converts a {@link UserApp} entity into a {@link UserAppDTO}.
     * <p>
     * Maps all user fields including the associated roles. If the user
     * has no roles assigned, an empty collection is used. The
     * {@code dateUpdated} field is included only if it is not null.
     * </p>
     *
     * @param userApp the user entity to convert
     * @return a {@link UserAppDTO} populated with the entity's data
     */
    public UserAppDTO convertEntityToDto(UserApp userApp) {

        Collection<RoleDTO> rolesDto = new ArrayList<>();
        if (userApp.getRoles() != null && !userApp.getRoles().isEmpty()) {
            for (Role role : userApp.getRoles()) {
                rolesDto.add(new RoleDTO(role.getId(), role.getName(), role.getDescription()));
            }
        }

        return new UserAppDTO(userApp.getId(), userApp.getFirstname(), userApp.getLastname(), userApp.getUsername(), userApp.getDateCreated(),
                userApp.getDateUpdated(), rolesDto);
    }

    /**
     * Converts a {@link UserAppRequest} DTO into a {@link UserApp} entity.
     * <p>
     * Maps the basic user fields and transforms each {@link RoleDTO}
     * into a {@link Role} entity. If no roles are provided in the request,
     * an empty set is assigned to the user.
     * </p>
     *
     * @param userAppDTO the request object containing the user data to convert
     * @return a {@link UserApp} entity populated with the request's data
     */
    public UserApp convertRequestToEntity(UserAppRequest userAppDTO) {
        Set<Role> roles = new HashSet<Role>();
        if (userAppDTO.getRoles() != null && !userAppDTO.getRoles().isEmpty()) {
            for (RoleDTO roleDto : userAppDTO.getRoles()) {
                roles.add(new Role(roleDto.getId(), roleDto.getAuthority(), roleDto.getDescription()));
            }
        }

        return new UserApp(userAppDTO.getFirstname(), userAppDTO.getUsername(), userAppDTO.getLastname(), roles);
    }

    /**
     * Converts a {@link UserAppDTO} into a {@link UserApp} entity.
     * <p>
     * Maps the basic user fields and transforms each {@link RoleDTO}
     * into a {@link Role} entity. If no roles are provided,
     * an empty set is assigned to the user.
     * </p>
     *
     * @param userAppDTO the DTO containing the user data to convert
     * @return a {@link UserApp} entity populated with the DTO's data
     */
    public UserApp convertDtoToEntity(UserAppDTO userAppDTO) {
        Set<Role> roles = new HashSet<Role>();
        if (userAppDTO.getRoles() != null && !userAppDTO.getRoles().isEmpty()) {
            for (RoleDTO roleDto : userAppDTO.getRoles()) {
                roles.add(new Role(roleDto.getId(), roleDto.getAuthority(), roleDto.getDescription()));
            }
        }

        return new UserApp(userAppDTO.getFirstname(), userAppDTO.getUsername(), userAppDTO.getLastname(), roles);
    }
}
