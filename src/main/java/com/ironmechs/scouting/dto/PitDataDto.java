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
@Schema( name = "PitData" )
public
class PitDataDto implements Serializable {
    @Schema( description = "Official team number issued by FIRST.",
             example = "5684",
             maxLength = 10,
             minLength = 1 )
    private Integer teamNumber;
    @Schema( description = "Height of robot at starting configuration in inches.",
             example = "60.0" )
    private Double height;
    @Schema( description = "Dimensions of the base of the robot at starting configuration in inches.",
             example = "12.5x16" )
    private String dimensions;
    @Schema( description = "Does the team have multiple drive teams or do they use the same drive team every match.",
             example = "true" )
    private Boolean multipleDriveTeams;
    @Schema( description = "Can the robot shoot on the low goal.",
             example = "true" )
    private Boolean canShootLow;
    @Schema( description = "Can the robot shoot on the high goal.",
             example = "false" )
    private Boolean canShootHigh;
    @Schema( description = "Can the climb on the lowest rung.",
             example = "true" )
    private Boolean canClimbLow;
    @Schema( description = "Can the climb on the middle rung.",
             example = "true" )
    private Boolean canClimbMiddle;
    @Schema( description = "Can the climb on the highest rung.",
             example = "false" )
    private Boolean canClimbHigh;
    @Schema( description = "Can the climb from one rung to the ohter.",
             example = "true" )
    private Boolean canClimbTraversal;
    @Schema( description = "How much cargo is the robot capable of holding at at time.",
             example = "2" )
    private Integer holdingCapacity;
}
