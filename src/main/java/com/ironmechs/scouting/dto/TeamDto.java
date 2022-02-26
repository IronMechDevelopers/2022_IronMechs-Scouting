package com.ironmechs.scouting.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema( name = "Team" )
public
class TeamDto implements Serializable {
    @Schema( description = "TBA team key with the format frcXXXX with XXXX representing the team number.",
             example = "frc5684",
             maxLength = 10,
             minLength = 4 )
    private String key;
    @Schema( description = "Official team number issued by FIRST.",
             example = "5684",
             maxLength = 10,
             minLength = 1 )
    private int teamNumber;

    @Schema( description = "Team nickname provided by FIRST.",
             example = "Iron Mechs" )
    private String nickname;
    @Schema( description = "Official long name registered with FIRST.",
             example = "Lockheed Martin/2015 FRC Rookie Grant/Picatinny Arsenal/Mission Solutions LLC/Tuchman Foundation&Trenton Catholic Academy-Upper" )
    private String name;

    @Schema( description = "Name of team school or affiliated group registered with FIRST.",
             example = "Trenton Catholic Academy-Upper" )
    private String school_name;
    @Schema( description = "City of team derived from parsing the address registered with FIRST.",
             example = "Trenton" )
    private String city;
    @Schema( description = "State of team derived from parsing the address registered with FIRST.",
             example = "New Jersey" )
    private String state_prov;
    @Schema( description = "Country of team derived from parsing the address registered with FIRST.",
             example = "USA" )
    private String country;
    @Schema( description = "Postal code from the team address.",
             example = "08610" )
    private String postal_code;
    @Schema( description = "Official website associated with the team.",
             example = "https://trentoncatholic.org/robotics" )
    private String website;
    @Schema( description = "First year the team officially competed.",
             example = "2015" )
    private int rookie_year;
}
