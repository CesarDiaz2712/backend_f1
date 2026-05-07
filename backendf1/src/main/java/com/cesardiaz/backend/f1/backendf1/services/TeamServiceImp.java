package com.cesardiaz.backend.f1.backendf1.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cesardiaz.backend.f1.backendf1.components.TeamConvertDTO;
import com.cesardiaz.backend.f1.backendf1.core.advice.BadRequestCustomException;
import com.cesardiaz.backend.f1.backendf1.core.advice.ResourceNotFoundException;
import com.cesardiaz.backend.f1.backendf1.core.constants.ErrorKeyEnum;
import com.cesardiaz.backend.f1.backendf1.dtos.TeamDTO;
import com.cesardiaz.backend.f1.backendf1.models.TeamFormulaOne;
import com.cesardiaz.backend.f1.backendf1.projections.TeamView;
import com.cesardiaz.backend.f1.backendf1.repositories.TeamRepository;
import com.cesardiaz.backend.f1.backendf1.utils.TeamCommandEnum;
import com.cesardiaz.backend.f1.backendf1.utils.validation.TeamValidationRequest;


@Service
public class TeamServiceImp implements TeamService {

    private final TeamValidationRequest teamValidationRequest;
    private final TeamRepository teamRepository;
    private final TeamConvertDTO teamConvertDTO;

    public TeamServiceImp(TeamValidationRequest teamValidationRequest, TeamRepository teamRepository,
            TeamConvertDTO teamConvertDTO) {
        this.teamValidationRequest = teamValidationRequest;
        this.teamRepository = teamRepository;
        this.teamConvertDTO = teamConvertDTO;

    }

    @Override
    public TeamDTO createTeamRace(TeamDTO teamDTO) {

        teamValidationRequest.validateParamsToCreate(teamDTO);

        TeamFormulaOne team = teamConvertDTO.convertDtoToEntity(teamDTO);

        Optional<TeamFormulaOne> teamExist = teamRepository.findByName(team.getName());

        if (teamExist.isPresent()) {
            throw new BadRequestCustomException(ErrorKeyEnum.DATA_DUPLICATED);
        }

        team.setDateCreated(new Date());

        teamRepository.save(team);

        return teamConvertDTO.convertEntityToDTO(team);

    }

    @Override
    public TeamDTO getTeamById(Long teamId) {

        Optional<TeamFormulaOne> teamOptional = teamRepository.findById(teamId);

        if (!teamOptional.isPresent()) {
            throw new ResourceNotFoundException(ErrorKeyEnum.NOT_FOUND);
        }

        return teamConvertDTO.convertEntityToDTO(teamOptional.get());
    }

    @Override
    public Page<TeamView> getTeams(TeamCommandEnum command, int page, int size) {

        if (command == null)
            throw new BadRequestCustomException(ErrorKeyEnum.BAD_REQUEST);

        if (command.equals(TeamCommandEnum.actual_teams)) {
            return teamRepository.findAllTeams(PageRequest.of(page, size, Sort.by("name")), true);
        }

        return teamRepository.findAllTeams(PageRequest.of(page, size, Sort.by("name")), false);

    }

}
