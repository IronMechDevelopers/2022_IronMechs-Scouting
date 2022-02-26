package com.ironmechs.scouting.controller;

import com.ironmechs.scouting.dto.PitDataDto;
import com.ironmechs.scouting.service.PitDataService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
            @Valid PitDataDto _pitData)
    {
        log.info("Making a create pitData {}.",
                 _pitData);
        try {
            PitDataDto pitData = service.save(_pitData);
            return status(HttpStatus.CREATED).body(pitData);
        } catch (Exception e) {
            log.error("Error in createTeam.",
                      e);
            return status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
