package com.github.syndexmx.demoairfleet.controllers.mappers;

import com.github.syndexmx.demoairfleet.controllers.dtos.AircraftDto;
import com.github.syndexmx.demoairfleet.domain.AircraftFields;
import com.github.syndexmx.demoairfleet.domain.Aircraft;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AircraftDtoMapper {

    public static AircraftDto aircraftToAircraftDto(Aircraft aircraft) {
        final AircraftDto aircraftDto = AircraftDto.builder()
                .id(aircraft.getId().toString())
                .aircraftFieldContent(aircraft.getAircraftFields().toString())
                .build();
        return aircraftDto;
    }

    public static Aircraft aircraftDtoToAircraft(AircraftDto aircraftDto) {
        Aircraft aircraft = Aircraft.builder()
                .id(UUID.fromString(aircraftDto.getId()))
                .aircraftFields(AircraftFields.valueOf(aircraftDto.getAircraftFieldContent()))
                .build();
        return aircraft;
    }

    public static Aircraft aircraftDtoNoIdToAircraft(AircraftDto aircraftDto) {
        Aircraft aircraft = Aircraft.builder()
                .id(UUID.randomUUID())
                .aircraftFields(AircraftFields.valueOf(aircraftDto.getAircraftFieldContent()))
                .build();
        return aircraft;
    }

}
