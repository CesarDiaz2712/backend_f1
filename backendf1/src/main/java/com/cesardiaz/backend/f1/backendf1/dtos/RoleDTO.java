package com.cesardiaz.backend.f1.backendf1.dtos;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
public class RoleDTO {
    private Long id;
    
    private String authority;
    
    private String description;


    public RoleDTO(Long id, String authority, String description) {
        this.id = id;
        this.authority = authority;
        this.description = description;
    }    
    
    public RoleDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
