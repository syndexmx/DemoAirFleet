package com.github.syndexmx.demoairfleet.controller.mappers;

import com.github.syndexmx.demoairfleet.controller.dtos.FlightDto;
import com.github.syndexmx.demoairfleet.domain.Flight;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class FlightDtoMapper {

    public FlightDto flightToFlightDto(Flight flight) {
        final FlightDto flightDto = FlightDto.builder()
                .id(flight.getId().toString())
                .name(flight.getName())
                .route(flight.getRoute())
                .pax(flight.getPax())
                .callsign(flight.getCallsign())
                .date(flight.getDate().toString())
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
                .build();
        return flight;
    }

}
