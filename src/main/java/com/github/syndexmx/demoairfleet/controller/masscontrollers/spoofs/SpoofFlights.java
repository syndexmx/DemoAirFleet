package com.github.syndexmx.demoairfleet.controller.masscontrollers.spoofs;

import com.github.syndexmx.demoairfleet.domain.Aircraft;
import com.github.syndexmx.demoairfleet.domain.Flight;
import com.github.syndexmx.demoairfleet.domain.Pilot;

import java.time.LocalDate;
import java.util.UUID;

public class SpoofFlights {

    public static Flight getFlight(Long index, Aircraft aircraft,
                                   Pilot captain, Pilot firstOfficer) {
        return Flight.builder()
                .id(UUID.randomUUID())
                .aircraft(aircraft)
                .captain(captain)
                .firstOfficer(firstOfficer)
                .callsign("Delta8473")
                .pax(206)
                .date(LocalDate.parse("2010-09-15"))
                .route("MH-48")
                .build();
    }
}
