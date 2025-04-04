package com.github.syndexmx.demoairfleet.controller.masscontrollers.spoofs;

import com.github.syndexmx.demoairfleet.domain.Aircraft;
import com.github.syndexmx.demoairfleet.domain.Engine;
import com.github.syndexmx.demoairfleet.domain.enums.AircraftType;

import java.time.LocalDate;
import java.util.List;

public class SpoofAircrafts {

    public static Aircraft getAircraft(Long index, List<Engine> engineList) {

        return Aircraft.builder()
                .id(index)
                .aircraftType(AircraftType.BOEING_737_600)
                .serialNumber("84935")
                .pax(211)
                .engineCount(2)
                .hoursInFlight(13053)
                .lastInspection(LocalDate.parse("2025-02-02"))
                .livery("Snow White livery with a narrow horizontal orange stripe")
                .registration("RU19834")
                .startOperation(LocalDate.parse("2010-03-14"))
                .engineList(engineList)
                .build();
    }

}
