package com.github.syndexmx.demoairfleet.repository.entities;


import com.github.syndexmx.demoairfleet.domain.AircraftType;
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
@Table(name = "aircrafts")
public class AircraftEntity {

    @Id
    private UUID id;
    private String aircraftType;
    private String serialNumber;
    private String registration;
    private String livery;
    private Integer pax;
    private Integer engineCount;
    private Integer hoursInFlight;
    private LocalDate startService;
    private LocalDate lastInspection;

}
