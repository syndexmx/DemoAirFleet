package com.github.syndexmx.demoairfleet.controller.mappers;


import com.github.syndexmx.demoairfleet.controller.dtos.AircraftDto;
import com.github.syndexmx.demoairfleet.domain.AircraftType;
import com.github.syndexmx.demoairfleet.domain.Aircraft;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Component
public class AircraftDtoMapper {

    private static Map<String, AircraftType> typeNameToTypeType = new HashMap<>();

    public AircraftDtoMapper() {
        for (AircraftType aircraftType : AircraftType.values()) {
            typeNameToTypeType.put(aircraftType.getTypeName(), aircraftType);
        }
    }


    public AircraftDto aircraftToAircraftDto(Aircraft aircraft) {
        final AircraftDto aircraftDto = AircraftDto.builder()
                .id(aircraft.getId().toString())
                .aircraftType(aircraft.getAircraftType().getTypeName())
                .lastInspection(aircraft.getLastInspection().toString())
                .startService(aircraft.getStartService().toString())
                .engineCount(aircraft.getEngineCount())
                .hoursInFlight(aircraft.getHoursInFlight())
                .livery(aircraft.getLivery())
                .pax(aircraft.getPax())
                .registration(aircraft.getRegistration())
                .serialNumber(aircraft.getSerialNumber())
                .build();
        return aircraftDto;
    }

    public Aircraft aircraftDtoToAircraft(AircraftDto aircraftDto) {
        Aircraft aircraft = Aircraft.builder()
                .id(UUID.fromString(aircraftDto.getId()))
                .aircraftType(typeNameToTypeType.get(aircraftDto.getAircraftType()))
                .lastInspection(LocalDate.parse(aircraftDto.getLastInspection()))
                .startService(LocalDate.parse(aircraftDto.getStartService()))
                .engineCount(aircraftDto.getEngineCount())
                .hoursInFlight(aircraftDto.getHoursInFlight())
                .livery(aircraftDto.getLivery())
                .pax(aircraftDto.getPax())
                .registration(aircraftDto.getRegistration())
                .serialNumber(aircraftDto.getSerialNumber())
                .build();
        return aircraft;
    }

    public Aircraft aircraftDtoNoIdToAircraft(AircraftDto aircraftDto) {
        Aircraft aircraft = Aircraft.builder()
                .id(UUID.randomUUID())
                .aircraftType(typeNameToTypeType.get(aircraftDto.getAircraftType()))
                .lastInspection(LocalDate.parse(aircraftDto.getLastInspection()))
                .startService(LocalDate.parse(aircraftDto.getStartService()))
                .engineCount(aircraftDto.getEngineCount())
                .hoursInFlight(aircraftDto.getHoursInFlight())
                .livery(aircraftDto.getLivery())
                .pax(aircraftDto.getPax())
                .registration(aircraftDto.getRegistration())
                .serialNumber(aircraftDto.getSerialNumber())
                .build();
        return aircraft;
    }

}
