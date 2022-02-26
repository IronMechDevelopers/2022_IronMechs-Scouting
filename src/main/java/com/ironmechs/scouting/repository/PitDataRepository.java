package com.ironmechs.scouting.repository;

import com.ironmechs.scouting.domain.PitData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public
interface PitDataRepository extends JpaRepository<PitData, String> {
    List<PitData> findByTeam_TeamNumber(int teamNumber);

    Optional<PitData> findFirstByTeam_TeamNumberOrderByUpdateDateTimeDesc(int teamNumber);
}
