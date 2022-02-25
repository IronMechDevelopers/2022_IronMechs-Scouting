package com.ironmechs.scouting.mapper;

import com.ironmechs.scouting.domain.Team;
import com.ironmechs.scouting.dto.TeamDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith( SpringExtension.class ) // JUnit 5
@ContextConfiguration( classes = {TeamMapperImpl.class} )
class TeamMapperTest {

    @Autowired
    TeamMapper mapper;

    @Test
    void teamDtoToTeam() {
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

        Team nullTeam = mapper.teamDtoToTeam(null);

        Team team = mapper.teamDtoToTeam(teamDto);

        assertNull(nullTeam);
        assertNotNull(team);
        assertNull(team.getId());
        assertEquals("frc5684",
                     team.getKey());
        assertEquals(5684,
                     team.getTeamNumber());
        assertEquals("Iron Mech",
                     team.getNickname());
        assertEquals("Lockheed Martin/2015 FRC Rookie Grant/Picatinny Arsenal/Mission Solutions LLC/Tuchman " +
                     "Foundation&Trenton Catholic Academy-Upper",
                     team.getName());
        assertEquals("Trenton Catholic Academy-Upper",
                     team.getSchool_name());
        assertEquals("Trenton",
                     team.getCity());
        assertEquals("New Jersey",
                     team.getState_prov());
        assertEquals("USA",
                     team.getCountry());
        assertEquals("08610",
                     team.getPostal_code());
        assertEquals("https://trentoncatholic.org/robotics",
                     team.getWebsite());
        assertEquals(2015,
                     team.getRookie_year());
    }

    @Test
    void teamToTeamDto() {

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

        TeamDto nullDTO = mapper.teamToTeamDto(null);

        TeamDto teamDto = mapper.teamToTeamDto(team);

        assertNull(nullDTO);
        assertNotNull(teamDto);
        assertEquals("frc5684",
                     teamDto.getKey());
        assertEquals(5684,
                     teamDto.getTeamNumber());
        assertEquals("Iron Mech",
                     teamDto.getNickname());
        assertEquals("Lockheed Martin/2015 FRC Rookie Grant/Picatinny Arsenal/Mission Solutions LLC/Tuchman " +
                     "Foundation&Trenton Catholic Academy-Upper",
                     teamDto.getName());
        assertEquals("Trenton Catholic Academy-Upper",
                     teamDto.getSchool_name());
        assertEquals("Trenton",
                     teamDto.getCity());
        assertEquals("New Jersey",
                     teamDto.getState_prov());
        assertEquals("USA",
                     teamDto.getCountry());
        assertEquals("08610",
                     teamDto.getPostal_code());
        assertEquals("https://trentoncatholic.org/robotics",
                     teamDto.getWebsite());
        assertEquals(2015,
                     teamDto.getRookie_year());
    }

    @Test
    void updateTeamFromTeamDto() {
        Team team = Team
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

        String oldId = team.getId();
        mapper.updateTeamFromTeamDto(teamDto,
                                     team);

        assertNotNull(team);
        assertEquals(oldId,
                     team.getId());
        assertEquals("frc5684",
                     team.getKey());
        assertEquals(5684,
                     team.getTeamNumber());
        assertEquals("Iron Mech",
                     team.getNickname());
        assertEquals("Lockheed Martin/2015 FRC Rookie Grant/Picatinny Arsenal/Mission Solutions LLC/Tuchman " +
                     "Foundation&Trenton Catholic Academy-Upper",
                     team.getName());
        assertEquals("Trenton Catholic Academy-Upper",
                     team.getSchool_name());
        assertEquals("Trenton",
                     team.getCity());
        assertEquals("New Jersey",
                     team.getState_prov());
        assertEquals("USA",
                     team.getCountry());
        assertEquals("08610",
                     team.getPostal_code());
        assertEquals("https://trentoncatholic.org/robotics",
                     team.getWebsite());
        assertEquals(2015,
                     team.getRookie_year());
    }
}
