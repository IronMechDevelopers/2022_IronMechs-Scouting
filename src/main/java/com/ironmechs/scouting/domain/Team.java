package com.ironmechs.scouting.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "team",
        uniqueConstraints = {@UniqueConstraint( name = "uc_team_teamnumber",
                                                columnNames = {"team_Number"} )} )
public
class Team {
    @JsonIgnore
    @Id
    private String id;
    @CreationTimestamp
    private LocalDateTime createDateTime;
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
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
    @Lob
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
    @OneToMany( mappedBy = "team",
                fetch = javax.persistence.FetchType.LAZY,
                cascade = javax.persistence.CascadeType.ALL )
    private Collection<PitData> pitData;

    @PrePersist
    protected
    void onCreate() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }

    @Override
    public
    boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Team team = (Team) o;
        return id != null &&
               Objects.equals(id,
                              team.id);
    }

    @Override
    public
    int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public
    String toString() {
        return getClass().getSimpleName() +
               "(" +
               "id = " +
               id +
               ", " +
               "createDateTime = " +
               createDateTime +
               ", " +
               "updateDateTime = " +
               updateDateTime +
               ", " +
               "key = " +
               key +
               ", " +
               "teamNumber = " +
               teamNumber +
               ", " +
               "nickname = " +
               nickname +
               ", " +
               "name = " +
               name +
               ", " +
               "school_name = " +
               school_name +
               ", " +
               "city = " +
               city +
               ", " +
               "state_prov = " +
               state_prov +
               ", " +
               "country = " +
               country +
               ", " +
               "postal_code = " +
               postal_code +
               ", " +
               "website = " +
               website +
               ", " +
               "rookie_year = " +
               rookie_year +
               ")";
    }
}
