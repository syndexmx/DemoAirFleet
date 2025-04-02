package com.github.syndexmx.demoairfleet.repositories.mappers;


import com.github.syndexmx.demoairfleet.domain.AircraftFields;
import com.github.syndexmx.demoairfleet.domain.Aircraft;
import com.github.syndexmx.demoairfleet.repositories.entities.AircraftEntity;
import org.springframework.stereotype.Component;

@Component
public class AircraftEntityMapper {

    public static AircraftEntity aircraftToAircraftEntity(Aircraft aircraft) {
        final AircraftEntity aircraftEntity = AircraftEntity.builder()
                .aircraftId(aircraft.getId())
                .aircraftFieldContent(aircraft.getAircraftFields().toString())
                .build();
        return aircraftEntity;
    }

    public static Aircraft aircraftEntityToAircraft(AircraftEntity aircraftEntity) {
        Aircraft aircraft = Aircraft.builder()
                .id(aircraftEntity.getAircraftId())
                .aircraftFields(AircraftFields.valueOf(aircraftEntity.getAircraftFieldContent()))
                .build();
        return aircraft;
    }



}
