package com.cesardiaz.backend.f1.backendf1.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cesardiaz.backend.f1.backendf1.helpers.UserHelper;
import com.cesardiaz.backend.f1.backendf1.models.Role;
import com.cesardiaz.backend.f1.backendf1.models.UserApp;
import com.cesardiaz.backend.f1.backendf1.repositories.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @InjectMocks
    private UserHelper userHelper;
    

    @BeforeEach
    public void init(){
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1l));

        UserApp user =  userHelper.createUserDefault();
        userRepository.save(user);
    }

    @Test
    public void findUserByUsernameUnsuccess(){

        Optional<UserApp> userOptional = userRepository.findByUsername("EXAMPLE");

        assertThat(userOptional).isNotPresent();
    }

    @Test
    public void findUserByUsernameSuccess(){

        Optional<UserApp> userOptional = userRepository.findByUsername("stricline");

        assertThat(userOptional).isPresent().isNotNull();
    }
}
