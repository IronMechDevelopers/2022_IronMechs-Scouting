package com.ironmechs.scouting.repository;

import com.ironmechs.scouting.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public
interface TeamRepository extends JpaRepository<Team, String> {
    Optional<Team> findByTeamNumber(@NonNull
                                             int teamNumber);
}
