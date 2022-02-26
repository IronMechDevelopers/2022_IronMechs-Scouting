package com.ironmechs.scouting.mapper;

import com.ironmechs.scouting.domain.PitData;
import com.ironmechs.scouting.domain.Team;
import com.ironmechs.scouting.dto.PitDataDto;
import com.ironmechs.scouting.service.TeamService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
class PitDataMapperTest {

    @Mock
    TeamService service;
    @InjectMocks
    PitDataMapperImpl mapper;

    @Test
    void nullTest()
    {
        PitDataDto pitDataDto = mapper.pitDataToPitDataDto(null);
        assertNull(pitDataDto);

        PitData pitData = mapper.pitDataDtoToPitData(null);
        assertNull(pitData);
    }

    @Test
    void pitDataToPitDataDto()
    {
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

        PitDataDto pitDataDto = mapper.pitDataToPitDataDto(pitData);

        assertEquals(5684,
                     pitDataDto.getTeamNumber());
        assertEquals(60.0,
                     pitDataDto.getHeight());
        assertEquals("12.5x16",
                     pitDataDto.getDimensions());
        assertEquals(false,
                     pitDataDto.getMultipleDriveTeams());
        assertEquals(true,
                     pitDataDto.getCanClimbLow());
        assertEquals(false,
                     pitDataDto.getCanClimbHigh());
        assertEquals(true,
                     pitDataDto.getCanClimbMiddle());
        assertEquals(false,
                     pitDataDto.getCanShootHigh());
        assertEquals(false,
                     pitDataDto.getCanClimbTraversal());
        assertEquals(true,
                     pitDataDto.getCanShootLow());
        assertEquals(2,
                     pitDataDto.getHoldingCapacity());
    }

    @Test
    void pitDataDtoToPitData()
    {
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

        when(service.findByIdTeamNumber(5684)).thenReturn(team);

        PitData pitData = mapper.pitDataDtoToPitData(pitDataDto);

        assertEquals(team,
                     pitData.getTeam());
        assertEquals(60.0,
                     pitData.getHeight());
        assertEquals("12.5x16",
                     pitData.getDimensions());
        assertEquals(false,
                     pitData.isMultipleDriveTeams());
        assertEquals(false,
                     pitData.isCanClimbHigh());
        assertEquals(true,
                     pitData.isCanClimbMiddle());
        assertEquals(false,
                     pitData.isCanShootHigh());
        assertEquals(false,
                     pitData.isCanClimbTraversal());
        assertEquals(true,
                     pitData.isCanShootLow());
        assertEquals(2,
                     pitData.getHoldingCapacity());
    }

    @Test
    void pitDataDtoToPitDataNullTeam()
    {
        PitDataDto pitDataDto = PitDataDto
                .builder()
                .teamNumber(null)
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

        PitData pitData = mapper.pitDataDtoToPitData(pitDataDto);

        assertNull(pitData.getTeam());
        assertEquals(60.0,
                     pitData.getHeight());
        assertEquals("12.5x16",
                     pitData.getDimensions());
        assertEquals(false,
                     pitData.isMultipleDriveTeams());
        assertEquals(false,
                     pitData.isCanClimbHigh());
        assertEquals(false,
                     pitData.isCanClimbHigh());
        assertEquals(true,
                     pitData.isCanClimbMiddle());
        assertEquals(false,
                     pitData.isCanShootHigh());
        assertEquals(false,
                     pitData.isCanClimbTraversal());
        assertEquals(true,
                     pitData.isCanShootLow());
        assertEquals(2,
                     pitData.getHoldingCapacity());
    }

    @Test
    void pitDataDtoToPitDataNoTeam()
    {
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

        PitData pitData = mapper.pitDataDtoToPitData(pitDataDto);

        assertNull(pitData.getTeam());
        assertEquals(60.0,
                     pitData.getHeight());
        assertEquals("12.5x16",
                     pitData.getDimensions());
        assertEquals(false,
                     pitData.isMultipleDriveTeams());
        assertEquals(false,
                     pitData.isCanClimbHigh());
        assertEquals(false,
                     pitData.isCanClimbHigh());
        assertEquals(true,
                     pitData.isCanClimbMiddle());
        assertEquals(false,
                     pitData.isCanShootHigh());
        assertEquals(false,
                     pitData.isCanClimbTraversal());
        assertEquals(true,
                     pitData.isCanShootLow());
        assertEquals(2,
                     pitData.getHoldingCapacity());
    }


    @Test
    void updatePitDataFromPitDataDto() {
        Team nemesis = Team
                .builder()
                .id(UUID.randomUUID().toString())
                .key("frc2590")
                .teamNumber(2590)
                .nickname("Nemesis")
                .name("NAVAIR  /  DoDSTEM / Bristol Myers Squibb / CCL Label / Picatinny Arsenal / Lockheed Martin / New York Society of Cosmetic Chemists / Nordson / Robbinsville Education Association / Princeton Sports and Family Medicine / Investors Foundation / Robbinsville Education Foundation / DesignTree / RAS Process Equipment / Sharbell Development Corp / Skylink / Triangle Copy East Windsor / Computer Components Corp / Gilbane Inc. / EAG Laboratories & Robbinsville High School")
                .school_name("Robbinsville High School")
                .city("Robbinsville")
                .state_prov("Pennsylvania")
                .country("CAN")
                .postal_code("08691")
                .website("http://frc2590.org/")
                .rookie_year(2008)
                .build();

        Team ironMechs = Team
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
                .team(ironMechs)
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

        when(service.findByIdTeamNumber(2590)).thenReturn(nemesis);

        mapper.updatePitDataFromPitDataDto(pitDataDto,
                                           pitData);

        assertEquals(nemesis,
                     pitData.getTeam());
        assertEquals(100.0,
                     pitData.getHeight());
        assertEquals("158",
                     pitData.getDimensions());
        assertEquals(true,
                     pitData.isMultipleDriveTeams());
        assertEquals(true,
                     pitData.isCanClimbHigh());
        assertEquals(true,
                     pitData.isCanClimbHigh());
        assertEquals(false,
                     pitData.isCanClimbMiddle());
        assertEquals(true,
                     pitData.isCanShootHigh());
        assertEquals(true,
                     pitData.isCanClimbTraversal());
        assertEquals(false,
                     pitData.isCanShootLow());
        assertEquals(1,
                     pitData.getHoldingCapacity());
    }

}
