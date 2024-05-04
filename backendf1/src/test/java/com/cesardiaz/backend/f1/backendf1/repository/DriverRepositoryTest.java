package com.cesardiaz.backend.f1.backendf1.repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import com.cesardiaz.backend.f1.backendf1.helpers.DriverHelper;
import com.cesardiaz.backend.f1.backendf1.helpers.UserHelper;
import com.cesardiaz.backend.f1.backendf1.models.DriverFormulaOne;
import com.cesardiaz.backend.f1.backendf1.models.Role;
import com.cesardiaz.backend.f1.backendf1.models.UserApp;
import com.cesardiaz.backend.f1.backendf1.projections.DriverDataView;
import com.cesardiaz.backend.f1.backendf1.repositories.DriverFormulaRepository;
import com.cesardiaz.backend.f1.backendf1.repositories.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback
public class DriverRepositoryTest {

    @Autowired
    DriverFormulaRepository driverFormulaRepository;
    
    @Autowired
    UserRepository userRepository;
    
    DriverFormulaOne driver = null;
    UserApp userByDriver=null;

    @InjectMocks
    DriverHelper driverHelper;
    
    @InjectMocks
    UserHelper userHelper;

    @BeforeEach
    public void init(){

        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1l));

        driver = driverHelper.createDriverFormulaDefaultWithUser();
        
        userRepository.save(driver.getUser());
        
        driverFormulaRepository.save(driver);
    }

    @Test
    public void saveNewDriverNotRepited(){
        
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1l));

        userByDriver = userHelper.createUserCustom("Cesar JEsus", "calejodiazas", "123");

        driver = driverHelper.createDriverFormulaCustom("Cesar", "Alejo", "Stricline8931s", "27", userByDriver);
        
        userRepository.save(userByDriver);
        
        DriverFormulaOne driverSaved = driverFormulaRepository.save(driver);

        assertThat(driverSaved).isNotNull();
    }
    
    @Test
    public void findDriverByGamertagSuccess(){

        Optional<DriverFormulaOne> driver = driverFormulaRepository.findByGamertag("Stricline8931");

        assertThat(driver).get().isNotNull();
    }

    
    @Test
    public void findDriverByGamertagNotFound(){
        Optional<DriverFormulaOne> driver = driverFormulaRepository.findByGamertag("Stricli");
        assertThat(driver).isEmpty();
    }

    
    @Test
    public void saveDriveSuccess(){
        Optional<DriverFormulaOne> driver = driverFormulaRepository.findByGamertag("Stricli");
        assertThat(driver).isEmpty();
    }

    @Test
    public void findAllDriversActive(){
        int page = 0;
        int size = 1;
        Page<DriverDataView> drivePage = driverFormulaRepository.findAllDriversByStatusActivated(PageRequest.of(page, size, Sort.by("id")));

        assertThat(drivePage).isNotEmpty();
    }
    
    @Test
    public void findAllDriversRegistred(){
        int page = 0;
        int size = 1;
        Page<DriverDataView> drivePage = driverFormulaRepository.findAllDrivers(PageRequest.of(page, size, Sort.by("id")));

        assertThat(drivePage).isNotEmpty();
    }
}
