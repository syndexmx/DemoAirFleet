package com.github.syndexmx.demoairfleet.controller.masscontrollers.spoofs;

import com.github.syndexmx.demoairfleet.domain.Engine;
import com.github.syndexmx.demoairfleet.domain.enums.EngineType;

import java.time.LocalDate;

public class SpoofEngines {

    public static Engine getEngine(Long index) {

        return Engine.builder()
                .id(index)
                .engineType(EngineType.GE_GE90)
                .serialNumber("776363")
                .hoursInFlight(27874)
                .lastInspection(LocalDate.parse("2024-12-10"))
                .startOperation(LocalDate.parse("2013-01-09"))
                .build();
    }
}
