package com.github.syndexmx.demoairfleet.controller.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

}
