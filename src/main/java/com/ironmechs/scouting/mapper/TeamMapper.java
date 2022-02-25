package com.ironmechs.scouting.mapper;

import com.ironmechs.scouting.domain.Team;
import com.ironmechs.scouting.dto.TeamDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper( unmappedTargetPolicy = ReportingPolicy.IGNORE,
         componentModel = "spring" )
public
interface TeamMapper {
    @Mapping( target = "id",
              ignore = true )
    @Mapping( target = "createDateTime",
              ignore = true )
    @Mapping( target = "updateDateTime",
              ignore = true )
    Team teamDtoToTeam(TeamDto teamDto);


    TeamDto teamToTeamDto(Team team);

    @Mapping( target = "id",
              ignore = true )
    @Mapping( target = "createDateTime",
              ignore = true )
    @Mapping( target = "updateDateTime",
              ignore = true )
    @BeanMapping( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
    void updateTeamFromTeamDto(TeamDto teamDto,
                               @MappingTarget
                                       Team team);
}
