package com.cesardiaz.backend.f1.backendf1.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesardiaz.backend.f1.backendf1.components.UserConverterDto;
import com.cesardiaz.backend.f1.backendf1.constans.MessageCustom;
import com.cesardiaz.backend.f1.backendf1.dtos.RoleDTO;
import com.cesardiaz.backend.f1.backendf1.dtos.UserAppDTO;
import com.cesardiaz.backend.f1.backendf1.models.Role;
import com.cesardiaz.backend.f1.backendf1.models.UserApp;
import com.cesardiaz.backend.f1.backendf1.repositories.UserRepository;
import com.cesardiaz.backend.f1.backendf1.utils.ResponseEntityCustom;
import com.cesardiaz.backend.f1.backendf1.utils.validation.UserAppValidationRequest;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserConverterDto userConverterDto;
    private final PasswordEncoder passwordEncoder;
    private final UserAppValidationRequest appValidationForm;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserConverterDto userConverterDto, PasswordEncoder passwordEncoder, UserAppValidationRequest appValidationForm) {
        this.userRepository = userRepository;
        this.userConverterDto = userConverterDto;
        this.passwordEncoder = passwordEncoder;
        this.appValidationForm = appValidationForm;
    }

    @Override
    public UserAppDTO createUser(UserAppDTO userAppDTO) {
        // TODO Auto-generated method stub

        UserApp user = this.userRepository.save(userConverterDto.convertDtoToEntity(userAppDTO));
        return this.userConverterDto.convertEntityToDto(user);
    }

    @Override
    public ResponseEntity<?> updateUser(Map<String,String> requestMap) {
        // TODO Auto-generated method stub

        if(appValidationForm.validateIdMap(requestMap)){

            try {
                
            Optional<UserApp> userOptional  = userRepository.findById(Long.parseLong(requestMap.get("id")));

            if(!userOptional.isPresent()){
                return ResponseEntity.notFound().build();
            }

            UserApp user = userOptional.get();
            boolean flag= false;

            if(requestMap.containsKey("username") && requestMap.get("username")!=null){
                user.setUsername(requestMap.get("username"));
                flag = true;
            }

            if(requestMap.containsKey("password") && requestMap.get("password")!=null){
                user.setUsername(passwordEncoder.encode(requestMap.get("password")));
                flag = true;
            }

            if(flag){
                user.setDateUpdated(LocalDate.now());
                return ResponseEntityCustom.getResponseEntity("User updated", HttpStatus.OK);
            }

            return ResponseEntity.badRequest().build();
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            return ResponseEntity.internalServerError().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<UserAppDTO> findUserById(Long id) {
        // TODO Auto-generated method stub

        if(id == null){
            return ResponseEntity.badRequest().build();
        }

        try {
         

        Optional<UserApp> userOptional = this.userRepository.findById(id);

        if(!userOptional.isPresent())
            return ResponseEntity.notFound().build();


        return ResponseEntity.ok().body(this.userConverterDto.convertEntityToDto(userOptional.get()));   
        
        } catch (Exception e) {
            // TODO: handle exception
               e.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();   
 
    }

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        // TODO Auto-generated method stub

        try {
            if(appValidationForm.validateSignUpMap(requestMap)){
                Optional<UserApp> userApp = userRepository.findByUsername(requestMap.get("username"));
                if(!userApp.isPresent()){
                    userRepository.save(getUserFromMap(requestMap));

                    return ResponseEntityCustom.getResponseEntity("Usuario registrado", HttpStatus.CREATED);
                }else{
                    
                    return ResponseEntityCustom.getResponseEntity("Usuario existente", HttpStatus.BAD_REQUEST);
                }
            }else{
                return ResponseEntityCustom.getResponseEntity(MessageCustom.INVALID_DATA, HttpStatus.BAD_REQUEST); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntityCustom.getResponseEntity(MessageCustom.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // private boolean validateSignUpMap(Map<String, String> requestMap){

    //     if(requestMap.containsKey("name") && requestMap.containsKey("username") && requestMap.containsKey("password")
    //     && requestMap.containsKey("role")){
    //         return true;
    //     }else
    //     return false;
    // }

    private UserApp getUserFromMap(Map<String, String> requestMap){

        if(requestMap!=null){

            String idRole = requestMap.get("role");

            Long idRoleLong= null;
            try {
                idRoleLong = Long.parseLong(idRole);
            } catch (Exception e) {
                // TODO: handle exception
            }

            UserApp userApp = new UserApp();
            userApp.setUsername(requestMap.get("username"));
            userApp.setPassword(passwordEncoder.encode(requestMap.get("password")));
            userApp.setName(requestMap.get("name"));
            userApp.setDateCreated(LocalDate.now());
            userApp.setRole(new Role(idRoleLong));
            
            return userApp;
        }else{
            throw new NullPointerException("RequestMap is null" + requestMap);
        }
    }
    
}
