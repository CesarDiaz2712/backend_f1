package com.cesardiaz.backend.f1.backendf1.dtos;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserAppDTO {

    
	@JsonInclude(value = Include.NON_NULL)
    private Long id;

	@JsonInclude(value = Include.NON_NULL)
    private String name;

	@JsonInclude(value = Include.NON_NULL)
    private String username;

	@JsonInclude(value = Include.NON_NULL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

	@JsonInclude(value = Include.NON_NULL)
    private String dateCreated;

	@JsonInclude(value = Include.NON_NULL)
    private String dateUpdated;
    
	@JsonInclude(value = Include.NON_NULL)
    private Collection<RoleDTO> roles = new ArrayList<>();
    
    public UserAppDTO(Long id, String name, String username, String dateCreated,
    String dateUpdated, Collection<RoleDTO> roles) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.roles = roles;
    }
    
    public UserAppDTO(Long id) {
        this.id = id;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Collection<RoleDTO> getRoles() {
        return roles;
    }

    public void setRole(RoleDTO role) {
        this.roles.add(role);
    }

    
}
