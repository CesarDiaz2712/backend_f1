package com.cesardiaz.backend.f1.backendf1.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesardiaz.backend.f1.backendf1.models.Permission;
import com.cesardiaz.backend.f1.backendf1.models.Role;
import com.cesardiaz.backend.f1.backendf1.models.UserApp;
import com.cesardiaz.backend.f1.backendf1.repositories.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserApp> usuarioOptional = userRepository.findByUsername(username);

        if (usuarioOptional.isEmpty()) {
            logger.error("Error en el Login: no existe el usuario " + username + " en el sistema!");
            throw new UsernameNotFoundException("Username: " + username + " no existe en el sistema!");
        }

        UserApp usuario = usuarioOptional.orElseThrow();
        List<GrantedAuthority> authorities = usuario.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

        return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, getAuthorities(usuario.getRoles()));
    }
    private Collection<? extends GrantedAuthority> getAuthorities(
        Collection<Role> roles) {
   
          return getGrantedAuthorities(getPrivileges(roles));
      }
      private List<String> getPrivileges(Collection<Role> roles) {
 
        List<String> privileges = new ArrayList<>();
        List<Permission> collection = new ArrayList<>();
        for (Role role : roles) {
            privileges.add(role.getName());
            collection.addAll(role.getPermission());
        }
        for (Permission item : collection) {
            privileges.add(item.getCode());
        }
        return privileges;
    }

       private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
