package com.github.syndexmx.demoairfleet.controller.masscontrollers.spoofs;

import com.github.syndexmx.demoairfleet.domain.Pilot;
import com.github.syndexmx.demoairfleet.domain.enums.AircraftType;
import com.github.syndexmx.demoairfleet.domain.enums.Sex;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class SpoofPilots {

    public static Pilot getPilot(Long index) {

        return Pilot.builder()
                .id(UUID.randomUUID())
                .firstName("John")
                .secondName("James")
                .lastName("Smith")
                .certifiedAircraftTypes(List.of(AircraftType.BOEING_737_NG,
                        AircraftType.BOEING_737_600))
                .sex(Sex.MALE)
                .birthDate(LocalDate.parse("1985-03-17"))
                .hoursInFlight(11093)
                .build();
    }
}
