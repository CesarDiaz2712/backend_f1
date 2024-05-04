package com.cesardiaz.backend.f1.backendf1.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cesardiaz.backend.f1.backendf1.helpers.DriverHelper;
import com.cesardiaz.backend.f1.backendf1.helpers.UserHelper;
import com.cesardiaz.backend.f1.backendf1.models.ContractDriver;
import com.cesardiaz.backend.f1.backendf1.models.ContractDriverKey;
import com.cesardiaz.backend.f1.backendf1.models.DriverFormulaOne;
import com.cesardiaz.backend.f1.backendf1.models.Role;
import com.cesardiaz.backend.f1.backendf1.models.TeamFormulaOne;
import com.cesardiaz.backend.f1.backendf1.models.UserApp;
import com.cesardiaz.backend.f1.backendf1.repositories.ContractDriverRepository;
import com.cesardiaz.backend.f1.backendf1.repositories.DriverFormulaRepository;
import com.cesardiaz.backend.f1.backendf1.repositories.TeamRepository;
import com.cesardiaz.backend.f1.backendf1.repositories.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback
public class ContractDriverRepositoryTest {

    @InjectMocks
    UserHelper userHelper;

    @InjectMocks
    DriverHelper driverHelper;
    
    @Autowired
    ContractDriverRepository contractDriverRepository;

    @Autowired
    DriverFormulaRepository driverFormulaRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    UserRepository userRepository;

    DriverFormulaOne driverWithContractActivated = null;
    DriverFormulaOne driverWithContractNoActivated = null;
    UserApp userByDriverActivated = null;
    UserApp userByDriverNonActivated = null;
    TeamFormulaOne team = null;


    Date initialDateContract = null;
    Date finalContractDate = null;

    @BeforeEach
    public void init() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1l));
        try {

            initialDateContract = formatter.parse("2024-04-23");
            finalContractDate = formatter.parse("2024-12-01");

            // Driver and User creation for adding contract activated
            driverWithContractActivated = driverHelper.createDriverFormulaDefaultWithUser();

            // Driver and User creation for adding contract no activated
            userByDriverNonActivated = userHelper.createUserCustom("Cesar JEsus", "stricline12a", "123");

            team = new TeamFormulaOne(null, "RED", "RD", "2024", "CARLO", "AS", new Date(), null);

            // Save user and driver with Contract Activated
            userByDriverActivated = userRepository.save(driverWithContractActivated.getUser());
            driverWithContractActivated = driverFormulaRepository.save(driverWithContractActivated);

            // Save user and driver with Contract No Activated
            userByDriverNonActivated = userRepository.save(userByDriverNonActivated);
            driverWithContractNoActivated = driverHelper.createDriverFormulaCustom("Cesar", "Alejo", "Stricline893131a",
                    "27", userByDriverActivated);
            driverWithContractNoActivated = driverFormulaRepository.save(driverWithContractNoActivated);

            team = teamRepository.save(team);

            contractDriverRepository.save(ContractDriver.instance(driverWithContractActivated, team,
                    initialDateContract, finalContractDate, new Date(), null));

            contractDriverRepository.save(ContractDriver.instance(driverWithContractNoActivated, team,
                    initialDateContract, finalContractDate, new Date(), null, false));

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void createContractDriver() {

        userByDriverActivated = userRepository.save(driverWithContractActivated.getUser());
        driverWithContractActivated = driverFormulaRepository.save(driverWithContractActivated);
        ContractDriver contractDriver = contractDriverRepository.save(ContractDriver.instance(
                driverWithContractActivated, team, initialDateContract, finalContractDate, new Date(), null));

        assertThat(contractDriver).isNotNull();

    }

    @Test
    public void findAllContractByTeamActivated() {
        List<ContractDriver> list = contractDriverRepository.findAllContractByTeamActivated();

        assertThat(list).isNotEmpty().size().isGreaterThan(0);
    }

    @Test
    public void findContractDriverByContractDriverKeyAndStatusActivatedSuccess() {
        ContractDriverKey key = new ContractDriverKey(driverWithContractActivated.getId(), team.getId());
        Optional<ContractDriver> optionalContract = contractDriverRepository
                .findContractDriverByContractDriverKeyAndStatus(key, true);

        assertThat(optionalContract).isPresent().isNotEmpty();
    }

    @Test
    public void findContractDriverByContractDriverKeyAndStatusActivatedUnsuccess() {
        ContractDriverKey key = new ContractDriverKey(anyLong(), anyLong());
        Optional<ContractDriver> optionalContract = contractDriverRepository
                .findContractDriverByContractDriverKeyAndStatus(key, true);

        assertThat(optionalContract).isNotPresent();
    }

    @Test
    public void findContractDriverByContractDriverKeyAndStatusNonActivatedSuccess() {
        ContractDriverKey key = new ContractDriverKey(driverWithContractNoActivated.getId(), team.getId());
        Optional<ContractDriver> optionalContract = contractDriverRepository
                .findContractDriverByContractDriverKeyAndStatus(key, false);

        assertThat(optionalContract).isPresent().isNotEmpty();
    }

    @Test
    public void findContractDriverByContractDriverKeyAndStatusNonActivatedUnsuccess() {
        ContractDriverKey key = new ContractDriverKey(anyLong(), anyLong());
        Optional<ContractDriver> optionalContract = contractDriverRepository
                .findContractDriverByContractDriverKeyAndStatus(key, false);

        assertThat(optionalContract).isNotPresent();
    }

}
