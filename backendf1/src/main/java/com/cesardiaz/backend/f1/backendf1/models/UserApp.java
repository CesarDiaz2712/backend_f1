package com.cesardiaz.backend.f1.backendf1.models;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_app")
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "UserApp.findAll", query = "SELECT u FROM UserApp u")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserApp extends AbstractPersistableCustom<Long>{


    @Column(name = "name", columnDefinition = "varchar(20)")
    private String name;

    @Column(name = "username", columnDefinition = "varchar(20)", unique = true)
    @NotBlank
    private String username;
    
    @Column(name = "password")
    @Size(min = 5, max = 20)
    @NotBlank
    private String password;

    @Column(name= "date_created")
    private LocalDate dateCreated;
    
    @Column(name= "date_updated")
    private LocalDate dateUpdated;

    // @JsonIgnoreProperties({"users", "handler", "hibernateLazyInitializer"})
    @ManyToMany(fetch = FetchType.LAZY)
   	@JoinTable(name = "role_user_app", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<Role>();

    @Transient
    private Role newRole;

    public UserApp(String name, String username, String password, Set<Role> roles) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public UserApp(Long id){
        setId(id);
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRole(Role role) {
        this.roles.add(role);
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDate dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Role getNewRole() {
        return newRole;
    }

    public void setNewRole(Role newRole) {
        this.newRole = newRole;
    }

    
}
