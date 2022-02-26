package com.ironmechs.scouting.service;

import com.ironmechs.scouting.domain.PitData;
import com.ironmechs.scouting.dto.PitDataDto;
import com.ironmechs.scouting.exceptions.NoTeamException;
import com.ironmechs.scouting.mapper.PitDataMapper;
import com.ironmechs.scouting.repository.PitDataRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public
class PitDataService {

    @Autowired
    PitDataRepository repository;
    @Autowired
    PitDataMapper mapper;
    @Autowired
    TeamService teamService;

    @Transactional()
    public
    PitDataDto save(PitDataDto _data) throws NoTeamException
    {
        log.info("save single pitData");
        log.trace("PitDataDto: {}.",
                  _data);
        PitData pitData = mapper.pitDataDtoToPitData(_data);
        log.trace("pitData: {}.",
                  pitData);
        if (pitData.getTeam() == null) {
            throw new NoTeamException("Could not find a team " + _data.getTeamNumber() + ".");
        }
        PitData response = repository.save(pitData);
        log.trace("response: {}.",
                  response);
        PitDataDto pitData_ = mapper.pitDataToPitDataDto(response);
        log.trace("pitData_: {}.",
                  pitData_);
        return pitData_;
    }

    @Transactional()
    public
    Optional<PitDataDto> getNewestPitDataForTeam(int teamNumber) {
        Optional<PitData> temp = repository.findFirstByTeam_TeamNumberOrderByUpdateDateTimeDesc(teamNumber);
        return temp.map(pitData -> mapper.pitDataToPitDataDto(pitData));
    }

}
