package com.github.syndexmx.demoairfleet.controller.dtos;


import com.github.syndexmx.demoairfleet.domain.Engine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AircraftDto {

    private Long id;
    private String aircraftType;
    private String serialNumber;
    private String registration;
    private String livery;
    private Integer pax;
    private Integer engineCount;
    private Integer hoursInFlight;
    private String startOperation;
    private String lastInspection;
    private List<EngineDto> engineList;

}
