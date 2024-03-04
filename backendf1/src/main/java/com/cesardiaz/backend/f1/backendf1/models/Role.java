package com.cesardiaz.backend.f1.backendf1.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@NamedQuery(name = "Role.findAll", query = "SELECT dr FROM Role dr")
@Table(name = "role")
public class Role extends AbstractPersistableCustom<Long>{
    

    @Column(name = "name", columnDefinition = "varchar(20)")
    private String name;

    public Role(Long id, String name) {
        setId(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}