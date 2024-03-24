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

    public UserAppDTO convertEntityToDto(UserApp userApp){

        Collection<RoleDTO> rolesDto= new ArrayList<>();
        if(userApp.getRoles()!=null && userApp.getRoles().size()>0){
            for(Role role : userApp.getRoles()){
                rolesDto.add(new RoleDTO(role.getId(), role.getAuthority(), role.getDescription()));
            }
        }

        return new UserAppDTO(userApp.getId(), userApp.getName(), userApp.getUsername(), userApp.getDateCreated().toString(),userApp.getDateUpdated()!=null?userApp.getDateUpdated().toString():null, rolesDto);
    }

    public UserApp convertDtoToEntity(UserAppDTO userAppDTO){
        Set<Role> roles= new HashSet<Role>();
        if(userAppDTO.getRoles()!=null && userAppDTO.getRoles().size()>0){
            for(RoleDTO roleDto : userAppDTO.getRoles()){
                roles.add(new Role(roleDto.getId(), roleDto.getAuthority(), roleDto.getDescription()));
            }
        }

        return new UserApp(userAppDTO.getName(), userAppDTO.getUsername(), userAppDTO.getPassword(), roles);
    }
}
