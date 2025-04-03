package com.github.syndexmx.demoairfleet.repository.mappers;


import com.github.syndexmx.demoairfleet.domain.AircraftType;
import com.github.syndexmx.demoairfleet.domain.Aircraft;
import com.github.syndexmx.demoairfleet.repository.entities.AircraftEntity;
import org.springframework.stereotype.Component;


@Component
public class AircraftEntityMapper {

    public AircraftEntity aircraftToAircraftEntity(Aircraft aircraft) {
        final AircraftEntity aircraftEntity = AircraftEntity.builder()
                .aircraftId(aircraft.getId())
                .aircraftFieldContent(aircraft.getAircraftType().toString())
                .build();
        return aircraftEntity;
    }

    public Aircraft aircraftEntityToAircraft(AircraftEntity aircraftEntity) {
        Aircraft aircraft = Aircraft.builder()
                .id(aircraftEntity.getAircraftId())
                .aircraftType(AircraftType.valueOf(aircraftEntity.getAircraftFieldContent()))
                .build();
        return aircraft;
    }



}
