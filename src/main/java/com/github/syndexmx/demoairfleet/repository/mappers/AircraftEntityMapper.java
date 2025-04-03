package com.github.syndexmx.demoairfleet.repository.mappers;


import com.github.syndexmx.demoairfleet.domain.enums.AircraftType;
import com.github.syndexmx.demoairfleet.domain.Aircraft;
import com.github.syndexmx.demoairfleet.repository.entities.AircraftEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AircraftEntityMapper {

    private EngineEntityMapper engineEntityMapper;

    @Autowired
    public AircraftEntityMapper(EngineEntityMapper engineEntityMapper) {
        this.engineEntityMapper = engineEntityMapper;
    }

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
                .engineList(aircraft.getEngineList().stream()
                        .map(engine -> engineEntityMapper
                                .engineToEngineEntity(engine))
                        .toList())
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
                .engineList(aircraftEntity.getEngineList().stream()
                        .map(engineEntity -> engineEntityMapper
                                .engineEntityToEngine(engineEntity))
                        .toList())
                .build();
        return aircraft;
    }



}
