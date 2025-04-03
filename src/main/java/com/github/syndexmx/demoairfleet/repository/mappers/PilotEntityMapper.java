package com.github.syndexmx.demoairfleet.repository.mappers;


import com.github.syndexmx.demoairfleet.domain.PilotField;
import com.github.syndexmx.demoairfleet.domain.Pilot;
import com.github.syndexmx.demoairfleet.repository.entities.PilotEntity;
import org.springframework.stereotype.Component;


@Component
public class PilotEntityMapper {

    public PilotEntity pilotToPilotEntity(Pilot pilot) {
        final PilotEntity pilotEntity = PilotEntity.builder()
                .pilotId(pilot.getId())
                .pilotFieldContent(pilot.getPilotField().toString())
                .build();
        return pilotEntity;
    }

    public Pilot pilotEntityToPilot(PilotEntity pilotEntity) {
        Pilot pilot = Pilot.builder()
                .id(pilotEntity.getPilotId())
                .pilotField(PilotField.valueOf(pilotEntity.getPilotFieldContent()))
                .build();
        return pilot;
    }



}
