package com.github.syndexmx.demoairfleet.repository.mappers;

import com.github.syndexmx.demoairfleet.domain.enums.AircraftType;
import com.github.syndexmx.demoairfleet.domain.Aircraft;
import com.github.syndexmx.demoairfleet.repository.entities.AircraftEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AircraftEntityMapper {

    public static AircraftEntity aircraftToAircraftEntity(Aircraft aircraft) {
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
                .engineList(aircraft.getEngineList().stream()
                        .map(engine -> EngineEntityMapper
                                .engineToEngineEntity(engine))
                        .toList())
                .build();
        return aircraftEntity;
    }

    public static Aircraft aircraftEntityToAircraft(AircraftEntity aircraftEntity) {
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
                .engineList(aircraftEntity.getEngineList().stream()
                        .map(engineEntity -> EngineEntityMapper
                                .engineEntityToEngine(engineEntity))
                        .toList())
                .build();
        return aircraft;
    }
}
