package com.github.syndexmx.demoairfleet.controller.dtos;

import com.github.syndexmx.demoairfleet.domain.Aircraft;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FlightDto {

    private String id;
    private String name;
    private String route;
    private String callsign;
    private Integer pax;
    private String date;
    private AircraftDto aircraft;
    private PilotDto captain;
    private PilotDto firstOfficer;

}
