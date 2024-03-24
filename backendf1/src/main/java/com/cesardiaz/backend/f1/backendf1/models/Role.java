package com.cesardiaz.backend.f1.backendf1.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NamedQuery(name = "Role.findAll", query = "SELECT dr FROM Role dr")
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
public class Role extends AbstractPersistableCustom<Long>{
    

    @Column(name = "name", columnDefinition = "varchar(45)")
    private String authority;
    
    @Column(name = "description", columnDefinition = "varchar(45)")
    private String description;

    
    public Role(Long id) {
        setId(id);
    }

    public Role(Long id, String authority, String description) {
        setId(id);
        this.authority = authority;
        this.description = description;
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