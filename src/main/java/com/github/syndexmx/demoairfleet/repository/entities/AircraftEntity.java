package com.github.syndexmx.demoairfleet.repository.entities;


import com.github.syndexmx.demoairfleet.domain.Engine;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "aircrafts")
@NamedEntityGraph(name = "aircraft_entity-graph",
        attributeNodes = @NamedAttributeNode("engineList"))
public class AircraftEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String aircraftType;
    private String serialNumber;
    private String registration;
    private String livery;
    private Integer pax;
    private Integer engineCount;
    private Integer hoursInFlight;
    private LocalDate startOperation;
    private LocalDate lastInspection;

    @OneToMany(cascade = CascadeType.DETACH)
    private List<EngineEntity> engineList;

}
