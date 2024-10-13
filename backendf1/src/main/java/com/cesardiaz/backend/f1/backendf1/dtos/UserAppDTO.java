package com.cesardiaz.backend.f1.backendf1.dtos;

import java.util.Date;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserAppDTO {

    
	@JsonInclude(value = Include.NON_NULL)
    private Long id;

	@JsonInclude(value = Include.NON_NULL)
    private String firstname;

	@JsonInclude(value = Include.NON_NULL)
    private String lastname;

	@JsonInclude(value = Include.NON_NULL)
    private String username;

	@JsonInclude(value = Include.NON_NULL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

	@JsonInclude(value = Include.NON_NULL)
    private Date dateCreated;

	@JsonInclude(value = Include.NON_NULL)
    private Date dateUpdated;
    
	@JsonInclude(value = Include.NON_NULL)
    private Collection<RoleDTO> roles;

    
}
