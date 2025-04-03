package com.github.syndexmx.demoairfleet.controller.mappers;

import com.github.syndexmx.demoairfleet.controller.dtos.PilotDto;
import com.github.syndexmx.demoairfleet.domain.enums.Sex;
import com.github.syndexmx.demoairfleet.domain.Pilot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class PilotDtoMapper {

    private AircraftDtoMapper aircraftDtoMapper;

    @Autowired
    public PilotDtoMapper(AircraftDtoMapper aircraftDtoMapper) {
        this.aircraftDtoMapper = aircraftDtoMapper;
    }

    public PilotDto pilotToPilotDto(Pilot pilot) {
        final PilotDto pilotDto = PilotDto.builder()
                .id(pilot.getId().toString())
                .sex(pilot.getSex().toString())
                .firstName(pilot.getFirstName())
                .secondName(pilot.getSecondName())
                .lastName(pilot.getLastName())
                .birthDate(pilot.getBirthDate().toString())
                .hoursInFlight(pilot.getHoursInFlight())
                .certifiedAircraftTypes(pilot.getCertifiedAircraftTypes().stream()
                        .map(aircraftTypeName -> aircraftTypeName.getTypeName())
                        .toList())
                .build();
        return pilotDto;
    }

    public Pilot pilotDtoToPilot(PilotDto pilotDto) {
        Pilot pilot = Pilot.builder()
                .id(UUID.fromString(pilotDto.getId()))
                .sex(Sex.valueOf(pilotDto.getSex()))
                .firstName(pilotDto.getFirstName())
                .secondName(pilotDto.getSecondName())
                .lastName(pilotDto.getLastName())
                .birthDate(LocalDate.parse(pilotDto.getBirthDate()))
                .hoursInFlight(pilotDto.getHoursInFlight())
                .certifiedAircraftTypes(pilotDto.getCertifiedAircraftTypes().stream()
                        .map(aircraftTypeName ->
                                aircraftDtoMapper.getTypeByName(aircraftTypeName))
                        .toList())
                .build();
        return pilot;
    }

    public Pilot pilotDtoNoIdToPilot(PilotDto pilotDto) {
        Pilot pilot = Pilot.builder()
                .id(UUID.randomUUID())
                .sex(Sex.valueOf(pilotDto.getSex()))
                .firstName(pilotDto.getFirstName())
                .secondName(pilotDto.getSecondName())
                .lastName(pilotDto.getLastName())
                .birthDate(LocalDate.parse(pilotDto.getBirthDate()))
                .hoursInFlight(pilotDto.getHoursInFlight())
                .certifiedAircraftTypes(pilotDto.getCertifiedAircraftTypes().stream()
                        .map(aircraftTypeName ->
                                aircraftDtoMapper.getTypeByName(aircraftTypeName))
                        .toList())
                .build();
        return pilot;
    }

}
