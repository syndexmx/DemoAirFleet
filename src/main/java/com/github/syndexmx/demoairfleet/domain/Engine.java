package com.github.syndexmx.demoairfleet.domain;

import com.github.syndexmx.demoairfleet.domain.enums.EngineType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Engine {

    private Long id;
    private EngineType engineType;
    private String serialNumber;
    private Integer hoursInFlight;
    private LocalDate startOperation;
    private LocalDate lastInspection;

}
