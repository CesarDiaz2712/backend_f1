package com.cesardiaz.backend.f1.backendf1.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cesardiaz.backend.f1.backendf1.models.TeamFormulaOne;
import com.cesardiaz.backend.f1.backendf1.repositories.TeamRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback
public class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @BeforeEach
    public void init(){

        TeamFormulaOne team = new TeamFormulaOne(null, "Aston Martin", "RD", "2024", "CARLO", "AS", new Date(), null);
        teamRepository.save(team);
    }

    @Test
    public void createTeam(){
        TeamFormulaOne team = new TeamFormulaOne(null, "RED", "RD", "2024", "CARLO", "AS", new Date(), null);
        TeamFormulaOne teamSaved = teamRepository.save(team);
        assertThat(teamSaved).isNotNull();
    }

    
    @Test
    public void findTeamByAproximationSuccess(){
        Optional<TeamFormulaOne> teamOptional = teamRepository.findByName("Aston");
        assertThat(teamOptional).isPresent().isNotNull();
    }

    @Test
    public void findTeamByAproximationUnsuccess(){
        Optional<TeamFormulaOne> teamOptional = teamRepository.findByName("Hass");
        assertThat(teamOptional).isNotPresent();
    }

}
