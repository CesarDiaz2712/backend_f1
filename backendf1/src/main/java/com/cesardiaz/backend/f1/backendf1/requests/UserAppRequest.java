package com.cesardiaz.backend.f1.backendf1.requests;

import com.cesardiaz.backend.f1.backendf1.dtos.RoleDTO;
import lombok.*;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserAppRequest {

    private String firstname;

    private String lastname;

    private String username;

    private String password;

    private Collection<RoleDTO> roles;

}
