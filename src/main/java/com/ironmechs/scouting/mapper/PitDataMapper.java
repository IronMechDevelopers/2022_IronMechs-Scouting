package com.ironmechs.scouting.mapper;

import com.ironmechs.scouting.domain.PitData;
import com.ironmechs.scouting.dto.PitDataDto;
import com.ironmechs.scouting.service.TeamService;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper( unmappedTargetPolicy = ReportingPolicy.IGNORE,
         componentModel = "spring",
         uses = {TeamService.class} )
public
interface PitDataMapper {
    @Mapping( source = "teamNumber",
              target = "team" )
    @Mapping( target = "id",
              ignore = true )
    @Mapping( target = "createDateTime",
              ignore = true )
    @Mapping( target = "updateDateTime",
              ignore = true )
    PitData pitDataDtoToPitData(PitDataDto pitDataDto);

    @Mapping( source = "team.teamNumber",
              target = "teamNumber" )
    PitDataDto pitDataToPitDataDto(PitData pitData);

    @Mapping( source = "teamNumber",
              target = "team" )
    @Mapping( target = "id",
              ignore = true )
    @Mapping( target = "createDateTime",
              ignore = true )
    @Mapping( target = "updateDateTime",
              ignore = true )
    @BeanMapping( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
    void updatePitDataFromPitDataDto(PitDataDto pitDataDto,
                                     @MappingTarget
                                             PitData pitData);
}
