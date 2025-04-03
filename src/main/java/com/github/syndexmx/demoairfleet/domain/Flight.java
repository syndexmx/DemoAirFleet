package com.github.syndexmx.demoairfleet.domain;

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
public class Flight {

    private UUID id;
    private String name;
    private String route;
    private String callsign;
    private Integer pax;
    private LocalDate date;
    private Aircraft aircraft;
    private Pilot captain;
    private Pilot firstOfficer;
}
