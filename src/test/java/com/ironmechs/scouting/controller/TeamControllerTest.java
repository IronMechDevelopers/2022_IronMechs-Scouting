package com.ironmechs.scouting.controller;

import com.ironmechs.scouting.dto.TeamDto;
import com.ironmechs.scouting.service.TeamService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
class TeamControllerTest {

    @Mock
    TeamService service;
    @InjectMocks
    TeamController controller;

    @Test
    @SuppressWarnings( value = "unchecked" )
    void getAllTeams() {

        TeamDto teamDto = TeamDto
                .builder()
                .key("frc5684")
                .teamNumber(5684)
                .nickname("Iron Mech")
                .name("Lockheed Martin/2015 FRC Rookie Grant/Picatinny Arsenal/Mission Solutions LLC/Tuchman Foundation&Trenton Catholic Academy-Upper")
                .school_name("Trenton Catholic Academy-Upper")
                .city("Trenton")
                .state_prov("New Jersey")
                .country("USA")
                .postal_code("08610")
                .website("https://trentoncatholic.org/robotics")
                .rookie_year(2015)
                .build();

        List<TeamDto> teams = List.of(teamDto);

        when(service.getAll()).thenReturn(teams);

        ResponseEntity<?> allTeamsResponse = controller.getAllTeams();

        assertNotNull(allTeamsResponse);
        assertEquals(HttpStatus.OK,
                     allTeamsResponse.getStatusCode());
        Object body = allTeamsResponse.getBody();
        assertNotNull(body);
        for (TeamDto dto : (List<TeamDto>) body) {
            assertNotNull(dto);
            assertEquals(teamDto,
                         dto);
        }
    }

    @Test
    void getAllTeamsNoTeams() {

        List<TeamDto> teams = List.of();

        when(service.getAll()).thenReturn(teams);

        ResponseEntity<?> allTeamsResponse = controller.getAllTeams();

        assertNotNull(allTeamsResponse);
        assertEquals(HttpStatus.NO_CONTENT,
                     allTeamsResponse.getStatusCode());
        Object body = allTeamsResponse.getBody();
        assertNull(body);
    }

    @Test
    void getAllTeamsError() {
        when(service.getAll()).thenThrow(new NullPointerException());

        ResponseEntity<?> allTeamsResponse = controller.getAllTeams();

        assertNotNull(allTeamsResponse);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                     allTeamsResponse.getStatusCode());
        Object body = allTeamsResponse.getBody();
        assertNull(body);
    }

    @Test
    void createTeam() {

        TeamDto teamDto = TeamDto
                .builder()
                .key("frc5684")
                .teamNumber(5684)
                .nickname("Iron Mech")
                .name("Lockheed Martin/2015 FRC Rookie Grant/Picatinny Arsenal/Mission Solutions LLC/Tuchman Foundation&Trenton Catholic Academy-Upper")
                .school_name("Trenton Catholic Academy-Upper")
                .city("Trenton")
                .state_prov("New Jersey")
                .country("USA")
                .postal_code("08610")
                .website("https://trentoncatholic.org/robotics")
                .rookie_year(2015)
                .build();

        when(service.save(teamDto)).thenReturn(teamDto);

        ResponseEntity<?> newTeamsResponse = controller.createTeam(teamDto);
        assertNotNull(newTeamsResponse);
        assertEquals(HttpStatus.CREATED,
                     newTeamsResponse.getStatusCode());
        Object body = newTeamsResponse.getBody();
        assertNotNull(body);
        assertEquals(body,
                     teamDto);
    }

    @Test
    void createTeamError() {

        TeamDto teamDto = TeamDto
                .builder()
                .key("frc5684")
                .teamNumber(5684)
                .nickname("Iron Mech")
                .name("Lockheed Martin/2015 FRC Rookie Grant/Picatinny Arsenal/Mission Solutions LLC/Tuchman Foundation&Trenton Catholic Academy-Upper")
                .school_name("Trenton Catholic Academy-Upper")
                .city("Trenton")
                .state_prov("New Jersey")
                .country("USA")
                .postal_code("08610")
                .website("https://trentoncatholic.org/robotics")
                .rookie_year(2015)
                .build();

        when(service.save(teamDto)).thenThrow(new NumberFormatException());

        ResponseEntity<?> newTeamsResponse = controller.createTeam(teamDto);
        assertNotNull(newTeamsResponse);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                     newTeamsResponse.getStatusCode());
        assertNull(newTeamsResponse.getBody());
    }

    @Test
    void findTeamByNumber() {
        TeamDto teamDto = TeamDto
                .builder()
                .key("frc5684")
                .teamNumber(5684)
                .nickname("Iron Mech")
                .name("Lockheed Martin/2015 FRC Rookie Grant/Picatinny Arsenal/Mission Solutions LLC/Tuchman Foundation&Trenton Catholic Academy-Upper")
                .school_name("Trenton Catholic Academy-Upper")
                .city("Trenton")
                .state_prov("New Jersey")
                .country("USA")
                .postal_code("08610")
                .website("https://trentoncatholic.org/robotics")
                .rookie_year(2015)
                .build();

        when(service.getByTeamNumber(5684)).thenReturn(Optional.of(teamDto));


        ResponseEntity<?> newTeamsResponse = controller.findTeamByNumber(5684);
        assertNotNull(newTeamsResponse);
        assertEquals(HttpStatus.OK,
                     newTeamsResponse.getStatusCode());
        Object body = newTeamsResponse.getBody();
        assertNotNull(body);
        assertEquals(body,
                     teamDto);
    }

    @Test
    void findTeamByNumberNoTeam() {
        when(service.getByTeamNumber(5684)).thenReturn(Optional.empty());

        ResponseEntity<?> newTeamsResponse = controller.findTeamByNumber(5684);
        assertNotNull(newTeamsResponse);
        assertEquals(HttpStatus.NO_CONTENT,
                     newTeamsResponse.getStatusCode());
        Object body = newTeamsResponse.getBody();
        assertNull(body);
    }

    @Test
    void findTeamByNumberError() {

        when(service.getByTeamNumber(5684)).thenThrow(new NumberFormatException());

        ResponseEntity<?> newTeamsResponse = controller.findTeamByNumber(5684);
        assertNotNull(newTeamsResponse);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                     newTeamsResponse.getStatusCode());
        assertNull(newTeamsResponse.getBody());
    }
}
