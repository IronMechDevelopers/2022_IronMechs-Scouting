package com.ironmechs.scouting.service;

import com.ironmechs.scouting.domain.Team;
import com.ironmechs.scouting.dto.TeamDto;
import com.ironmechs.scouting.mapper.TeamMapper;
import com.ironmechs.scouting.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
class TeamServiceTest {

    @Mock
    TeamRepository repository;
    @Mock
    TeamMapper teamMapper;
    @InjectMocks
    TeamService teamService;

    @Test
    void findAll() {
        Team team = Team
                .builder()
                .id(UUID.randomUUID().toString())
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

        List<Team> teams = List.of(team);

        when(repository.findAll()).thenReturn(teams);

        when(teamMapper.teamToTeamDto(team)).thenReturn(teamDto);

        List<TeamDto> allTeamsResponse = teamService.getAll();

        assertNotNull(allTeamsResponse);
        assertEquals(1,
                     allTeamsResponse.size());
        assertEquals(teamDto,
                     allTeamsResponse.get(0));

    }

    @Test
    void getByTeamNumber() {

        Team team = Team
                .builder()
                .id(UUID.randomUUID().toString())
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

        when(repository.findByTeamNumber(5684)).thenReturn(Optional.of(team));
        when(teamMapper.teamToTeamDto(team)).thenReturn(teamDto);

        Optional<TeamDto> teamResponse = teamService.getByTeamNumber(5684);


        assertNotNull(teamResponse);
        assertTrue(teamResponse.isPresent());
        assertEquals(teamDto,
                     teamResponse.get());

    }

    @Test
    void getByTeamNumberNoTeam() {

        when(repository.findByTeamNumber(5684)).thenReturn(Optional.empty());

        Optional<TeamDto> teamResponse = teamService.getByTeamNumber(5684);


        assertNotNull(teamResponse);
        assertFalse(teamResponse.isPresent());
    }

    @Test
    void save() {

        Team teamWithId = Team
                .builder()
                .id(UUID.randomUUID().toString())
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

        Team teamWithNoId = Team
                .builder()
                .id(null)
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

        when(teamMapper.teamDtoToTeam(teamDto)).thenReturn(teamWithNoId);
        when(repository.save(teamWithNoId)).thenReturn(teamWithId);
        when(teamMapper.teamToTeamDto(teamWithId)).thenReturn(teamDto);

        TeamDto teamDtoResponse = teamService.save(teamDto);

        assertNotNull(teamDtoResponse);
        assertEquals(teamDto,
                     teamDtoResponse);

    }
}
