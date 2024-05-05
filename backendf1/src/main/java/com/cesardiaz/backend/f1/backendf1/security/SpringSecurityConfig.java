package com.cesardiaz.backend.f1.backendf1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.cesardiaz.backend.f1.backendf1.security.filter.JWTAuthenticationFilter;
import com.cesardiaz.backend.f1.backendf1.security.filter.JWTValidationFilter;

@Configuration
public class SpringSecurityConfig {

   @Autowired
   private AuthenticationConfiguration authenticationConfiguration;

   @Bean
   AuthenticationManager authenticationManager() throws Exception {
      return authenticationConfiguration.getAuthenticationManager();
   }

   @Bean
   SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      return http.authorizeHttpRequests((authz) -> authz
            .requestMatchers(HttpMethod.POST, "/api/user").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/teams", "/api/team/{id}").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/user/{id}").hasAnyRole("ADMIN","SUPERUSER","USER")
            .requestMatchers(HttpMethod.GET, "/api/driver/{id}", "/api/drivers").hasAnyAuthority("ADMIN", "SUPERUSER","CREATE_DRIVER")
            .requestMatchers(HttpMethod.POST, "/api/driver","/api/team", "/api/contractdriver/{id}").hasAnyRole("ADMIN", "SUPERUSER")
            .requestMatchers(HttpMethod.PUT,  "/api/contractdriver/{id}").hasAnyRole("ADMIN", "SUPERUSER")
            .anyRequest().authenticated())

            .addFilter(new JWTAuthenticationFilter(authenticationManager()))
            .addFilter(new JWTValidationFilter(authenticationManager()))
            .csrf(config -> config.disable())
            .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .build();
   }

}
