package com.ironmechs.scouting.controller;

import com.ironmechs.scouting.dto.PitDataDto;
import com.ironmechs.scouting.exceptions.NoTeamException;
import com.ironmechs.scouting.service.PitDataService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping( "/api/pitData" )
@AllArgsConstructor
@Slf4j
public
class PitDataController {

    @Autowired
    PitDataService service;

    @PostMapping( "/" )
    public
    ResponseEntity<?> createPitData(
            @RequestBody( description = "FRC pit data to be scouted out at a competition.",
                          required = true,
                          content = @Content( schema = @Schema( implementation = PitDataDto.class ) ) )
            @Valid
            @org.springframework.web.bind.annotation.RequestBody
                    PitDataDto _pitData)
    {
        log.info("Making a create pitData {}.",
                 _pitData);
        try {
            PitDataDto pitData = service.save(_pitData);
            return status(HttpStatus.CREATED).body(pitData);
        } catch (NoTeamException e) {
            log.error("Could not save pit data because of a NoTeamException.",
                      e);
            return status(HttpStatus.BAD_REQUEST).body("Could not save pit data because could not find a team " +
                                                       _pitData.getTeamNumber());
        } catch (Exception e) {
            log.error("Error in createPitData.",
                      e);
            return status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping( "/teamNumber/{teamNumber}" )
    public
    ResponseEntity<?> findPitDataByTeamNumber(
            @PathVariable( "teamNumber" )
                    int teamNumber)
    {
        log.info("Getting pit data for team {}",
                 teamNumber);
        try {
            Optional<PitDataDto> pitData = service.getNewestPitDataForTeam(teamNumber);
            if (pitData.isPresent()) {
                return status(HttpStatus.OK).body(pitData.get());
            }
            return status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception e) {
            log.error("Error in findPitDataByTeamNumber.",
                      e);
            return status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping( "/teamNumber/{teamNumber}" )
    public
    ResponseEntity<?> deletePitDataByTeamNumber(
            @PathVariable( "teamNumber" )
                    int teamNumber)
    {
        log.info("Deleting pit data for team {}",
                 teamNumber);
        try {
            long deleted = service.deletePitDataForTeam(teamNumber);
            log.info("Deleted {} pit data for team {}.",
                     deleted,
                     teamNumber);
            if (deleted > 0) {
                return status(HttpStatus.OK).body(null);
            }
            return status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception e) {
            log.error("Error in deletePitDataByTeamNumber.",
                      e);
            return status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
