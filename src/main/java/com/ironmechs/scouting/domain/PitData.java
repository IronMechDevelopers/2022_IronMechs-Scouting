package com.ironmechs.scouting.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString( onlyExplicitlyIncluded = true )
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "pit_data" )
public
class PitData {
    @ToString.Include
    @JsonIgnore
    @Id
    @Column( nullable = false )
    private String id;
    @ToString.Include
    @CreationTimestamp
    private LocalDateTime createDateTime;
    @ToString.Include
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    @ManyToOne
    @JoinColumn( name = "team_id" )
    private Team team;

    private double height;
    private String dimensions;
    private boolean multipleDriveTeams;
    private boolean canShootLow;
    private boolean canShootHigh;
    private boolean canClimbLow;
    private boolean canClimbMiddle;
    private boolean canClimbHigh;
    private boolean canClimbTraversal;
    private int holdingCapacity;

    @PrePersist
    protected
    void onCreate() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
}
