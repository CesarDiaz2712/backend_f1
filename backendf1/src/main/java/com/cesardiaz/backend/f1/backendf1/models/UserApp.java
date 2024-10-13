package com.cesardiaz.backend.f1.backendf1.models;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cesardiaz.backend.f1.backendf1.dtos.UserAppDTO;
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
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_app")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@NamedQuery(name = "UserApp.findAll", query = "SELECT u FROM UserApp u")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserApp extends AbstractPersistableCustom<Long>{


    @Column(name = "firstname", columnDefinition = "varchar(45)", nullable = false)
    @NotBlank
    private String firstname;

    @Column(name = "lastname", columnDefinition = "varchar(45)", nullable = false)
    @NotBlank
    private String lastname;

    @Column(name = "username", columnDefinition = "varchar(20)", unique = true, nullable = false)
    @NotBlank
    private String username;
    
    @Column(name = "password", nullable = false)
    @NotBlank
    private String password;

    @Column(name= "date_created", nullable = false)
    private Date dateCreated;
    
    @Column(name= "date_updated")
    private Date dateUpdated;

    @ManyToMany(fetch = FetchType.EAGER)
   	@JoinTable(name = "role_user_app", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Transient
    private Role newRole;

    @PostConstruct
    public void init(){
        roles =new HashSet<Role>();
    }

    public UserApp(String name, String username, String lastname, Set<Role> roles) {
        this.firstname = name;
        this.username = username;
        this.lastname = lastname;
        this.roles = roles;
    }

    public UserApp(Long id){
        setId(id);
    }

    public UserApp updateInfoUser(UserAppDTO userAppDTO){

        if (userAppDTO.getUsername() != null && !userAppDTO.getUsername().equals(this.username)) {
            setUsername(userAppDTO.getUsername());
        }

        if (userAppDTO.getFirstname() != null && !userAppDTO.getFirstname().equals(firstname)) {
            setFirstname(userAppDTO.getFirstname());;
        }
        
        if (userAppDTO.getLastname() != null && !userAppDTO.getLastname().equals(lastname)) {
            setLastname(userAppDTO.getLastname());;
        }
        
        return this;
    }

    public void addNewRole(Role role){
        roles.add(role);
    }
    
}
