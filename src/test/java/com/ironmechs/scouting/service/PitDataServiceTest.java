package com.ironmechs.scouting.service;

import com.ironmechs.scouting.domain.PitData;
import com.ironmechs.scouting.domain.Team;
import com.ironmechs.scouting.dto.PitDataDto;
import com.ironmechs.scouting.exceptions.NoTeamException;
import com.ironmechs.scouting.mapper.PitDataMapper;
import com.ironmechs.scouting.repository.PitDataRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
class PitDataServiceTest {

    @Mock
    PitDataRepository repository;
    @Mock
    PitDataMapper mapper;
    @InjectMocks
    PitDataService service;

    @SneakyThrows
    @Test
    void save() {
        PitDataDto pitDataDto = PitDataDto
                .builder()
                .teamNumber(5684)
                .height(60.0)
                .dimensions("12.5x16")
                .multipleDriveTeams(false)
                .canClimbLow(true)
                .canClimbHigh(false)
                .canClimbMiddle(true)
                .canClimbTraversal(false)
                .canShootHigh(false)
                .canShootLow(true)
                .holdingCapacity(2)
                .build();

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

        PitData pitData = PitData
                .builder()
                .team(team)
                .height(60.0)
                .dimensions("12.5x16")
                .multipleDriveTeams(false)
                .canClimbLow(true)
                .canClimbHigh(false)
                .canClimbMiddle(true)
                .canClimbTraversal(false)
                .canShootHigh(false)
                .canShootLow(true)
                .holdingCapacity(2)
                .build();
        PitData pitDataWithId = PitData
                .builder()
                .id(UUID.randomUUID().toString())
                .team(team)
                .height(60.0)
                .dimensions("12.5x16")
                .multipleDriveTeams(false)
                .canClimbLow(true)
                .canClimbHigh(false)
                .canClimbMiddle(true)
                .canClimbTraversal(false)
                .canShootHigh(false)
                .canShootLow(true)
                .holdingCapacity(2)
                .build();

        when(mapper.pitDataDtoToPitData(pitDataDto)).thenReturn(pitData);
        when(repository.save(pitData)).thenReturn(pitDataWithId);
        when(mapper.pitDataToPitDataDto(pitDataWithId)).thenReturn(pitDataDto);
        PitDataDto response = service.save(pitDataDto);

        assertNotNull(response);
        assertEquals(pitDataDto,
                     response);
    }

    @Test
    void saveNoTeam() {
        PitDataDto pitDataDto = PitDataDto
                .builder()
                .teamNumber(5684)
                .height(60.0)
                .dimensions("12.5x16")
                .multipleDriveTeams(false)
                .canClimbLow(true)
                .canClimbHigh(false)
                .canClimbMiddle(true)
                .canClimbTraversal(false)
                .canShootHigh(false)
                .canShootLow(true)
                .holdingCapacity(2)
                .build();

        PitData pitData = PitData
                .builder()
                .team(null)
                .height(60.0)
                .dimensions("12.5x16")
                .multipleDriveTeams(false)
                .canClimbLow(true)
                .canClimbHigh(false)
                .canClimbMiddle(true)
                .canClimbTraversal(false)
                .canShootHigh(false)
                .canShootLow(true)
                .holdingCapacity(2)
                .build();

        when(mapper.pitDataDtoToPitData(pitDataDto)).thenReturn(pitData);
        NoTeamException response = assertThrows(NoTeamException.class,
                                                () -> service.save(pitDataDto));

        assertNotNull(response);
        assertEquals("Could not find a team 5684.",
                     response.getMessage());

    }

    @Test
    void getNewestPitDataForTeamTest() {

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

        PitData pitData = PitData
                .builder()
                .team(team)
                .height(60.0)
                .dimensions("12.5x16")
                .multipleDriveTeams(false)
                .canClimbLow(true)
                .canClimbHigh(false)
                .canClimbMiddle(true)
                .canClimbTraversal(false)
                .canShootHigh(false)
                .canShootLow(true)
                .holdingCapacity(2)
                .build();

        PitDataDto pitDataDto = PitDataDto
                .builder()
                .teamNumber(5684)
                .height(60.0)
                .dimensions("12.5x16")
                .multipleDriveTeams(false)
                .canClimbLow(true)
                .canClimbHigh(false)
                .canClimbMiddle(true)
                .canClimbTraversal(false)
                .canShootHigh(false)
                .canShootLow(true)
                .holdingCapacity(2)
                .build();

        when(repository.findFirstByTeam_TeamNumberOrderByUpdateDateTimeDesc(5684)).thenReturn(Optional.of(pitData));
        when(mapper.pitDataToPitDataDto(pitData)).thenReturn(pitDataDto);

        Optional<PitDataDto> response = service.getNewestPitDataForTeam(5684);

        assertTrue(response.isPresent());
        assertEquals(pitDataDto,
                     response.get());
    }

    @Test
    void getNewestPitDataForTeamNoDataTest() {
        when(repository.findFirstByTeam_TeamNumberOrderByUpdateDateTimeDesc(5684)).thenReturn(Optional.empty());

        Optional<PitDataDto> response = service.getNewestPitDataForTeam(5684);

        assertFalse(response.isPresent());
    }
}
