package com.github.syndexmx.demoairfleet.repository.mappers;

import com.github.syndexmx.demoairfleet.domain.enums.AircraftType;
import com.github.syndexmx.demoairfleet.domain.enums.Sex;
import com.github.syndexmx.demoairfleet.domain.Pilot;
import com.github.syndexmx.demoairfleet.repository.entities.PilotEntity;
import org.springframework.stereotype.Component;

@Component
public class PilotEntityMapper {

    public PilotEntity pilotToPilotEntity(Pilot pilot) {
        final PilotEntity pilotEntity = PilotEntity.builder()
                .id(pilot.getId())
                .sex(pilot.getSex().toString())
                .firstName(pilot.getFirstName())
                .secondName(pilot.getSecondName())
                .lastName(pilot.getLastName())
                .birthDate(pilot.getBirthDate())
                .hoursInFlight(pilot.getHoursInFlight())
                .certifiedAircraftTypes(pilot.getCertifiedAircraftTypes().stream()
                        .map(aircraftTypeName -> aircraftTypeName.toString())
                        .toList())
                .build();
        return pilotEntity;
    }

    public Pilot pilotEntityToPilot(PilotEntity pilotEntity) {
        Pilot pilot = Pilot.builder()
                .id(pilotEntity.getId())
                .sex(Sex.valueOf(pilotEntity.getSex()))
                .firstName(pilotEntity.getFirstName())
                .secondName(pilotEntity.getSecondName())
                .lastName(pilotEntity.getLastName())
                .birthDate(pilotEntity.getBirthDate())
                .hoursInFlight(pilotEntity.getHoursInFlight())
                .certifiedAircraftTypes(pilotEntity.getCertifiedAircraftTypes().stream()
                        .map(aircraftTypeName ->
                                AircraftType.valueOf(aircraftTypeName))
                        .toList())
                .build();
        return pilot;
    }



}
