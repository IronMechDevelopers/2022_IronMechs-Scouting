package com.ironmechs.scouting.service;

import com.ironmechs.scouting.domain.Team;
import com.ironmechs.scouting.dto.TeamDto;
import com.ironmechs.scouting.mapper.TeamMapper;
import com.ironmechs.scouting.repository.TeamRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public
class TeamService {

    @Autowired
    TeamRepository repository;
    @Autowired
    TeamMapper teamMapper;

    @Transactional( readOnly = true )
    public
    List<TeamDto> getAll() {
        log.info("get all teams");
        List<Team> allTeams = repository.findAll();
        return allTeams.stream().map(t -> teamMapper.teamToTeamDto(t)).collect(Collectors.toList());
    }

    @Transactional( readOnly = true )
    public
    Team findByIdTeamNumber(int teamNumber) {
        log.info("getByTeamNumber {}.",
                 teamNumber);
        return repository.findByTeamNumber(teamNumber).orElse(null);
    }

    @Transactional( readOnly = true )
    public
    Optional<TeamDto> getByTeamNumber(int teamNumber) {
        log.info("getByTeamNumber {}.",
                 teamNumber);
        Optional<Team> team = repository.findByTeamNumber(teamNumber);
        return team.map(value -> teamMapper.teamToTeamDto(value));
    }

    @Transactional()
    public
    TeamDto save(TeamDto _team) {
        log.info("save single team");
        log.trace("teamDto: {}.", _team);
        Team team = teamMapper.teamDtoToTeam(_team);
        log.trace("team: {}.", team);
        Team response = repository.save(team);
        log.trace("response: {}.", response);
        TeamDto team_ = teamMapper.teamToTeamDto(response);
        log.trace("team_: {}.", team_);
        return team_;
    }
}
