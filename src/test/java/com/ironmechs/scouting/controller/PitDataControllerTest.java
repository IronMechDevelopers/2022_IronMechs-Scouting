package com.ironmechs.scouting.controller;

import com.ironmechs.scouting.dto.PitDataDto;
import com.ironmechs.scouting.exceptions.NoTeamException;
import com.ironmechs.scouting.service.PitDataService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
class PitDataControllerTest {

    @Mock
    PitDataService service;
    @InjectMocks
    PitDataController controller;

    @SneakyThrows
    @Test
    void createPitDataBadTeam() {

        PitDataDto pitDataDto = PitDataDto
                .builder()
                .teamNumber(5684)
                .height(100.0)
                .dimensions("158")
                .multipleDriveTeams(true)
                .canClimbLow(false)
                .canClimbHigh(true)
                .canClimbMiddle(false)
                .canClimbTraversal(true)
                .canShootHigh(true)
                .canShootLow(false)
                .holdingCapacity(1)
                .build();

        when(service.save(pitDataDto)).thenThrow(new NoTeamException("Could not find a team 5684."));

        ResponseEntity<?> repsonse = controller.createPitData(pitDataDto);
        assertNotNull(repsonse);
        assertEquals(HttpStatus.BAD_REQUEST,
                     repsonse.getStatusCode());
        Object body = repsonse.getBody();
        assertNotNull(body);
        assertEquals("Could not save team because could not find a team 5684",
                     body);
    }

    @SneakyThrows
    @Test
    void createPitData() {

        PitDataDto pitDataDto = PitDataDto
                .builder()
                .teamNumber(2590)
                .height(100.0)
                .dimensions("158")
                .multipleDriveTeams(true)
                .canClimbLow(false)
                .canClimbHigh(true)
                .canClimbMiddle(false)
                .canClimbTraversal(true)
                .canShootHigh(true)
                .canShootLow(false)
                .holdingCapacity(1)
                .build();

        when(service.save(pitDataDto)).thenReturn(pitDataDto);

        ResponseEntity<?> repsonse = controller.createPitData(pitDataDto);
        assertNotNull(repsonse);
        assertEquals(HttpStatus.CREATED,
                     repsonse.getStatusCode());
        Object body = repsonse.getBody();
        assertNotNull(body);
        assertEquals(body,
                     pitDataDto);
    }

    @SneakyThrows
    @Test
    void createPitDataError() {

        PitDataDto pitDataDto = PitDataDto
                .builder()
                .teamNumber(2590)
                .height(100.0)
                .dimensions("158")
                .multipleDriveTeams(true)
                .canClimbLow(false)
                .canClimbHigh(true)
                .canClimbMiddle(false)
                .canClimbTraversal(true)
                .canShootHigh(true)
                .canShootLow(false)
                .holdingCapacity(1)
                .build();

        when(service.save(pitDataDto)).thenThrow(new NumberFormatException());

        ResponseEntity<?> newTeamsResponse = controller.createPitData(pitDataDto);
        assertNotNull(newTeamsResponse);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                     newTeamsResponse.getStatusCode());
        assertNull(newTeamsResponse.getBody());
    }
}
