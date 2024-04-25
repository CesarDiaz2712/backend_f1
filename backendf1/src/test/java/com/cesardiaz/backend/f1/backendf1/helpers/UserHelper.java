package com.cesardiaz.backend.f1.backendf1.helpers;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.cesardiaz.backend.f1.backendf1.models.Role;
import com.cesardiaz.backend.f1.backendf1.models.UserApp;

import jakarta.annotation.PostConstruct;

@Component
public class UserHelper {

    private Set<Role> roles;

    public UserHelper(){

    }

    @PostConstruct
    public void init(){
        roles = new HashSet<>();
        roles.add(new Role(1l));
    }

    public UserApp createUserDefault(){
        
        UserApp user = null;
        user = new UserApp("Cesar JEsus", "stricline", "123", roles);
        user.setDateCreated(LocalDate.now());
        return user;
    }

    public UserApp createUserCustom(String name, String username, String password){
        
        UserApp user = null;
        user = new UserApp(name, username, password, roles);
        user.setDateCreated(LocalDate.now());
        return user;
    }
}
