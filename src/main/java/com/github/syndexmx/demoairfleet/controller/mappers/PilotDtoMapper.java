package com.github.syndexmx.demoairfleet.controller.mappers;


import com.github.syndexmx.demoairfleet.controller.dtos.PilotDto;
import com.github.syndexmx.demoairfleet.domain.PilotField;
import com.github.syndexmx.demoairfleet.domain.Pilot;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class PilotDtoMapper {

    public PilotDto pilotToPilotDto(Pilot pilot) {
        final PilotDto pilotDto = PilotDto.builder()
                .id(pilot.getId().toString())
                .pilotFieldContent(pilot.getPilotField().toString())
                .build();
        return pilotDto;
    }

    public Pilot pilotDtoToPilot(PilotDto pilotDto) {
        Pilot pilot = Pilot.builder()
                .id(UUID.fromString(pilotDto.getId()))
                .pilotField(PilotField.valueOf(pilotDto.getPilotFieldContent()))
                .build();
        return pilot;
    }

    public Pilot pilotDtoNoIdToPilot(PilotDto pilotDto) {
        Pilot pilot = Pilot.builder()
                .id(UUID.randomUUID())
                .pilotField(PilotField.valueOf(pilotDto.getPilotFieldContent()))
                .build();
        return pilot;
    }

}
