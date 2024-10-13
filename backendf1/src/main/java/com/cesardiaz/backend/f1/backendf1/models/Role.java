package com.cesardiaz.backend.f1.backendf1.models;

import java.util.Collection;
import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NamedQuery(name = "Role.findAll", query = "SELECT dr FROM Role dr")
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Role extends AbstractPersistableCustom<Long>{
    

    @Column(name = "name", columnDefinition = "varchar(45)")
    private String name;
    
    @Column(name = "description", columnDefinition = "varchar(45)")
    private String description;

        // @JsonIgnoreProperties(value = "users")
    @ManyToMany(fetch = FetchType.LAZY)
   	@JoinTable(name = "role_user_app", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Collection<UserApp> users;
    
    @ManyToMany(fetch = FetchType.LAZY)
   	@JoinTable(name = "role_permission_system", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Collection<Permission> permission;

    
    @PostConstruct
    public void init(){
        users =new HashSet<UserApp>();
        permission =new HashSet<Permission>();
    }

    public Role(Long id) {
        setId(id);
    }

    public Role(Long id, String authority, String description) {
        setId(id);
        this.name = authority;
        this.description = description;
    }

    
}