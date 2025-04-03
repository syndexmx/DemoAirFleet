package com.github.syndexmx.demoairfleet.controller.dtos;

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
    private PilotDto captain;
    private PilotDto firstOfficer;

}
