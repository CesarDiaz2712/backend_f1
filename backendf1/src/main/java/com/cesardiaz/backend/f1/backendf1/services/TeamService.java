package com.cesardiaz.backend.f1.backendf1.services;

import org.springframework.data.domain.Page;

import com.cesardiaz.backend.f1.backendf1.dtos.TeamDTO;
import com.cesardiaz.backend.f1.backendf1.projections.TeamView;
import com.cesardiaz.backend.f1.backendf1.utils.TeamCommandEnum;

public interface TeamService {

    TeamDTO createTeamRace(TeamDTO teamDTO);

    TeamDTO getTeamById(Long teamId);

    Page<TeamView> getTeams(TeamCommandEnum command, int page, int size);
}
