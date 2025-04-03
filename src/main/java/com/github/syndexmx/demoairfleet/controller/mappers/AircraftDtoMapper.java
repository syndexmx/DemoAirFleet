package com.github.syndexmx.demoairfleet.controller.mappers;


import com.github.syndexmx.demoairfleet.controller.dtos.AircraftDto;
import com.github.syndexmx.demoairfleet.domain.AircraftType;
import com.github.syndexmx.demoairfleet.domain.Aircraft;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class AircraftDtoMapper {

    public AircraftDto aircraftToAircraftDto(Aircraft aircraft) {
        final AircraftDto aircraftDto = AircraftDto.builder()
                .id(aircraft.getId().toString())
                .aircraftFieldContent(aircraft.getAircraftType().toString())
                .build();
        return aircraftDto;
    }

    public Aircraft aircraftDtoToAircraft(AircraftDto aircraftDto) {
        Aircraft aircraft = Aircraft.builder()
                .id(UUID.fromString(aircraftDto.getId()))
                .aircraftType(AircraftType.valueOf(aircraftDto.getAircraftFieldContent()))
                .build();
        return aircraft;
    }

    public Aircraft aircraftDtoNoIdToAircraft(AircraftDto aircraftDto) {
        Aircraft aircraft = Aircraft.builder()
                .id(UUID.randomUUID())
                .aircraftType(AircraftType.valueOf(aircraftDto.getAircraftFieldContent()))
                .build();
        return aircraft;
    }

}
