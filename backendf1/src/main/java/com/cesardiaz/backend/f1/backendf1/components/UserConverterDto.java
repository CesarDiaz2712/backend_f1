package com.cesardiaz.backend.f1.backendf1.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.cesardiaz.backend.f1.backendf1.dtos.RoleDTO;
import com.cesardiaz.backend.f1.backendf1.dtos.UserAppDTO;
import com.cesardiaz.backend.f1.backendf1.models.Role;
import com.cesardiaz.backend.f1.backendf1.models.UserApp;

@Component
public class UserConverterDto {

    public UserAppDTO convertEntityToDto(UserApp userApp) {

        Collection<RoleDTO> rolesDto = new ArrayList<>();
        if (userApp.getRoles() != null && !userApp.getRoles().isEmpty()) {
            for (Role role : userApp.getRoles()) {
                rolesDto.add(new RoleDTO(role.getId(), role.getName(), role.getDescription()));
            }
        }

        return new UserAppDTO(userApp.getId(), userApp.getFirstname(), userApp.getLastname(), userApp.getUsername(),
                userApp.getPassword(), userApp.getDateCreated(),
                userApp.getDateUpdated() != null ? userApp.getDateUpdated() : null, rolesDto);
    }

    public UserApp convertDtoToEntity(UserAppDTO userAppDTO) {
        Set<Role> roles = new HashSet<Role>();
        if (userAppDTO.getRoles() != null && userAppDTO.getRoles().size() > 0) {
            for (RoleDTO roleDto : userAppDTO.getRoles()) {
                roles.add(new Role(roleDto.getId(), roleDto.getAuthority(), roleDto.getDescription()));
            }
        }

        return new UserApp(userAppDTO.getFirstname(), userAppDTO.getUsername(), userAppDTO.getLastname(), roles);
    }
}
