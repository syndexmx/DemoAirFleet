package com.github.syndexmx.demoairfleet.repository.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "flights")
public class FlightEntity {

    @Id
    private UUID id;
    private String name;
    private String route;
    private String callsign;
    private Integer pax;
    private LocalDate date;

    @ManyToOne(cascade = CascadeType.DETACH)
    private PilotEntity captain;

    @ManyToOne(cascade = CascadeType.DETACH)
    private PilotEntity firstOfficer;


}
