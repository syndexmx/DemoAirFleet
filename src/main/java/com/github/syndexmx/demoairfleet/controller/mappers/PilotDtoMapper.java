package com.github.syndexmx.demoairfleet.controller.mappers;


import com.github.syndexmx.demoairfleet.controller.dtos.PilotDto;
import com.github.syndexmx.demoairfleet.domain.enums.Sex;
import com.github.syndexmx.demoairfleet.domain.Pilot;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;


@Component
public class PilotDtoMapper {

    public PilotDto pilotToPilotDto(Pilot pilot) {
        final PilotDto pilotDto = PilotDto.builder()
                .id(pilot.getId().toString())
                .sex(pilot.getSex().toString())
                .firstName(pilot.getFirstName())
                .secondName(pilot.getSecondName())
                .lastName(pilot.getLastName())
                .birthDate(pilot.getBirthDate().toString())
                .hoursInFlight(pilot.getHoursInFlight())
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
                .build();
        return pilot;
    }

}
