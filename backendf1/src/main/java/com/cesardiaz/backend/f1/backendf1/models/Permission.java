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
@NamedQuery(name = "Permission.findAll", query = "SELECT p FROM Permission p")
@Table(name = "permission_system")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Permission extends AbstractPersistableCustom<Long>{

    @Column(name = "code", columnDefinition = "varchar(45)", nullable = false, unique = true)
    private String code;
    
    @Column(name = "entity_name", columnDefinition = "varchar(45)")
    private String entityName;

    @Column(name = "action_name", columnDefinition = "varchar(45)")
    private String actionName;

    @ManyToMany(fetch = FetchType.LAZY)
   	@JoinTable(name = "role_permission_system", joinColumns = @JoinColumn(name = "permission_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @PostConstruct
    public void init(){
        roles =new HashSet<Role>();
    }


    
}
