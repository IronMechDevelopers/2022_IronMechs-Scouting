package com.ironmechs.scouting.controller;

import com.ironmechs.scouting.dto.TeamDto;
import com.ironmechs.scouting.service.TeamService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping( "/api/team" )
@AllArgsConstructor
@Slf4j
public
class TeamController {

    @Autowired
    TeamService service;

    @GetMapping( "/" )
    public
    ResponseEntity<?> getAllTeams() {
        log.info("Making a request to getAllTeams.");
        try {
            List<TeamDto> teams = service.getAll();
            if (teams.isEmpty()) {
                return status(HttpStatus.NO_CONTENT).body(null);
            }
            return status(HttpStatus.OK).body(teams);
        } catch (Exception e) {
            log.error("Error in getAllTeams.",
                      e);
            return status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping( "/teamNumber/{teamNumber}" )
    public
    ResponseEntity<?> findTeamByNumber(
            @PathVariable( "teamNumber" )
                    int teamNumber)
    {
        log.info("Getting data for team {}",
                 teamNumber);
        try {
            Optional<TeamDto> team = service.getByTeamNumber(teamNumber);
            if (team.isPresent()) {
                return status(HttpStatus.OK).body(team.get());
            }
            return status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception e) {
            log.error("Error in findTeamByNumber.",
                      e);
            return status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping( "/" )
    public
    ResponseEntity<?> createTeam(
            @RequestBody( description = "FRC team information.",
                          required = true,
                          content = @Content( schema = @Schema( implementation = TeamDto.class ) ) )
            @Valid
            @org.springframework.web.bind.annotation.RequestBody
                    TeamDto _team)
    {
        log.info("Making a create team {}.",
                 _team);
        try {
            TeamDto team = service.save(_team);
            return status(HttpStatus.CREATED).body(team);
        } catch (Exception e) {
            log.error("Error in createTeam.",
                      e);
            return status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
