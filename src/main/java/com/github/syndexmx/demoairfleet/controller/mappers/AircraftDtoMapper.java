package com.github.syndexmx.demoairfleet.controller.mappers;

import com.github.syndexmx.demoairfleet.controller.dtos.AircraftDto;
import com.github.syndexmx.demoairfleet.domain.enums.AircraftType;
import com.github.syndexmx.demoairfleet.domain.Aircraft;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class AircraftDtoMapper {

    private EngineDtoMapper engineDtoMapper;

    private static Map<String, AircraftType> typeNameToTypeType = new HashMap<>();

    @Autowired
    public AircraftDtoMapper(EngineDtoMapper engineDtoMapper) {
        for (AircraftType aircraftType : AircraftType.values()) {
            typeNameToTypeType.put(aircraftType.getTypeName(), aircraftType);
        }
        this.engineDtoMapper = engineDtoMapper;
    }

    public AircraftType getTypeByName(String name) {
        return typeNameToTypeType.get(name);
    };

    public AircraftDto aircraftToAircraftDto(Aircraft aircraft) {
        final AircraftDto aircraftDto = AircraftDto.builder()
                .id(aircraft.getId())
                .aircraftType(aircraft.getAircraftType().getTypeName())
                .lastInspection(aircraft.getLastInspection().toString())
                .startOperation(aircraft.getStartOperation().toString())
                .engineCount(aircraft.getEngineCount())
                .hoursInFlight(aircraft.getHoursInFlight())
                .livery(aircraft.getLivery())
                .pax(aircraft.getPax())
                .registration(aircraft.getRegistration())
                .serialNumber(aircraft.getSerialNumber())
                .engineList(aircraft.getEngineList().stream()
                        .map(engineDto -> engineDtoMapper
                                .engineToEngineDto(engineDto))
                        .toList())
                .build();
        return aircraftDto;
    }

    public Aircraft aircraftDtoToAircraft(AircraftDto aircraftDto) {
        Aircraft aircraft = Aircraft.builder()
                .id(aircraftDto.getId())
                .aircraftType(typeNameToTypeType.get(aircraftDto.getAircraftType()))
                .lastInspection(LocalDate.parse(aircraftDto.getLastInspection()))
                .startOperation(LocalDate.parse(aircraftDto.getStartOperation()))
                .engineCount(aircraftDto.getEngineCount())
                .hoursInFlight(aircraftDto.getHoursInFlight())
                .livery(aircraftDto.getLivery())
                .pax(aircraftDto.getPax())
                .registration(aircraftDto.getRegistration())
                .serialNumber(aircraftDto.getSerialNumber())
                .engineList(aircraftDto.getEngineList().stream()
                        .map(engineDto -> engineDtoMapper
                                .engineDtoToEngine(engineDto))
                        .toList())
                .build();
        return aircraft;
    }

    public Aircraft aircraftDtoNoIdToAircraft(AircraftDto aircraftDto) {
        Aircraft aircraft = Aircraft.builder()
                .id(null)
                .aircraftType(typeNameToTypeType.get(aircraftDto.getAircraftType()))
                .lastInspection(LocalDate.parse(aircraftDto.getLastInspection()))
                .startOperation(LocalDate.parse(aircraftDto.getStartOperation()))
                .engineCount(aircraftDto.getEngineCount())
                .hoursInFlight(aircraftDto.getHoursInFlight())
                .livery(aircraftDto.getLivery())
                .pax(aircraftDto.getPax())
                .registration(aircraftDto.getRegistration())
                .serialNumber(aircraftDto.getSerialNumber())
                .engineList(aircraftDto.getEngineList().stream()
                        .map(engineDto -> engineDtoMapper
                                .engineDtoToEngine(engineDto))
                        .toList())
                .build();
        return aircraft;
    }

}
