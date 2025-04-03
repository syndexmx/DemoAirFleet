package com.github.syndexmx.demoairfleet.domain;


import com.github.syndexmx.demoairfleet.domain.enums.AircraftType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Aircraft {

    private UUID id;
    private AircraftType aircraftType;
    private String serialNumber;
    private String registration;
    private String livery;
    private Integer pax;
    private Integer engineCount;
    private Integer hoursInFlight;
    private LocalDate startOperation;
    private LocalDate lastInspection;

}
