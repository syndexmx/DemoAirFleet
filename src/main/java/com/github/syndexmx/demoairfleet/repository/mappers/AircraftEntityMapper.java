package com.github.syndexmx.demoairfleet.repository.mappers;


import com.github.syndexmx.demoairfleet.domain.enums.AircraftType;
import com.github.syndexmx.demoairfleet.domain.Aircraft;
import com.github.syndexmx.demoairfleet.repository.entities.AircraftEntity;
import org.springframework.stereotype.Component;


@Component
public class AircraftEntityMapper {

    public AircraftEntity aircraftToAircraftEntity(Aircraft aircraft) {
        final AircraftEntity aircraftEntity = AircraftEntity.builder()
                .id(aircraft.getId())
                .aircraftType(aircraft.getAircraftType().toString())
                .lastInspection(aircraft.getLastInspection())
                .startOperation(aircraft.getStartOperation())
                .engineCount(aircraft.getEngineCount())
                .hoursInFlight(aircraft.getHoursInFlight())
                .livery(aircraft.getLivery())
                .pax(aircraft.getPax())
                .registration(aircraft.getRegistration())
                .serialNumber(aircraft.getSerialNumber())
                .build();
        return aircraftEntity;
    }

    public Aircraft aircraftEntityToAircraft(AircraftEntity aircraftEntity) {
        Aircraft aircraft = Aircraft.builder()
                .id(aircraftEntity.getId())
                .aircraftType(AircraftType.valueOf(aircraftEntity.getAircraftType()))
                .lastInspection(aircraftEntity.getLastInspection())
                .startOperation(aircraftEntity.getStartOperation())
                .engineCount(aircraftEntity.getEngineCount())
                .hoursInFlight(aircraftEntity.getHoursInFlight())
                .livery(aircraftEntity.getLivery())
                .pax(aircraftEntity.getPax())
                .registration(aircraftEntity.getRegistration())
                .serialNumber(aircraftEntity.getSerialNumber())
                .build();
        return aircraft;
    }



}
