package com.github.syndexmx.demoairfleet.controller.mappers;

import com.github.syndexmx.demoairfleet.controller.dtos.FlightDto;
import com.github.syndexmx.demoairfleet.domain.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class FlightDtoMapper {

    private PilotDtoMapper pilotDtoMapper;
    private AircraftDtoMapper aircraftDtoMapper;

    @Autowired
    public FlightDtoMapper(PilotDtoMapper pilotDtoMapper, AircraftDtoMapper aircraftDtoMapper) {
        this.pilotDtoMapper = pilotDtoMapper;
        this.aircraftDtoMapper = aircraftDtoMapper;
    }

    public FlightDto flightToFlightDto(Flight flight) {
        final FlightDto flightDto = FlightDto.builder()
                .id(flight.getId().toString())
                .name(flight.getName())
                .route(flight.getRoute())
                .pax(flight.getPax())
                .callsign(flight.getCallsign())
                .date(flight.getDate().toString())
                .aircraft(aircraftDtoMapper.aircraftToAircraftDto(flight.getAircraft()))
                .captain(pilotDtoMapper.pilotToPilotDto(flight.getCaptain()))
                .firstOfficer(pilotDtoMapper.pilotToPilotDto(flight.getFirstOfficer()))
                .build();
        return flightDto;
    }

    public Flight flightDtoToFlight(FlightDto flightDto) {
        Flight flight = Flight.builder()
                .id(UUID.fromString(flightDto.getId()))
                .name(flightDto.getName())
                .route(flightDto.getRoute())
                .pax(flightDto.getPax())
                .callsign(flightDto.getCallsign())
                .date(LocalDate.parse(flightDto.getDate()))
                .aircraft(aircraftDtoMapper.aircraftDtoToAircraft(flightDto.getAircraft()))
                .captain(pilotDtoMapper.pilotDtoToPilot(flightDto.getCaptain()))
                .firstOfficer(pilotDtoMapper.pilotDtoToPilot(flightDto.getFirstOfficer()))
                .build();
        return flight;
    }

    public Flight flightDtoNoIdToFlight(FlightDto flightDto) {
        Flight flight = Flight.builder()
                .id(UUID.randomUUID())
                .name(flightDto.getName())
                .route(flightDto.getRoute())
                .pax(flightDto.getPax())
                .callsign(flightDto.getCallsign())
                .date(LocalDate.parse(flightDto.getDate()))
                .aircraft(aircraftDtoMapper.aircraftDtoToAircraft(flightDto.getAircraft()))
                .captain(pilotDtoMapper.pilotDtoToPilot(flightDto.getCaptain()))
                .firstOfficer(pilotDtoMapper.pilotDtoToPilot(flightDto.getFirstOfficer()))
                .build();
        return flight;
    }

}
