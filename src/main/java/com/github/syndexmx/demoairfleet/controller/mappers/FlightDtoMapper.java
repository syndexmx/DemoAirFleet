package com.github.syndexmx.demoairfleet.controller.mappers;


import com.github.syndexmx.demoairfleet.controller.dtos.FlightDto;
import com.github.syndexmx.demoairfleet.domain.FlightData;
import com.github.syndexmx.demoairfleet.domain.Flight;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class FlightDtoMapper {

    public FlightDto flightToFlightDto(Flight flight) {
        final FlightDto flightDto = FlightDto.builder()
                .id(flight.getId().toString())
                .flightFieldContent(flight.getFlightData().toString())
                .build();
        return flightDto;
    }

    public Flight flightDtoToFlight(FlightDto flightDto) {
        Flight flight = Flight.builder()
                .id(UUID.fromString(flightDto.getId()))
                .flightData(FlightData.valueOf(flightDto.getFlightFieldContent()))
                .build();
        return flight;
    }

    public Flight flightDtoNoIdToFlight(FlightDto flightDto) {
        Flight flight = Flight.builder()
                .id(UUID.randomUUID())
                .flightData(FlightData.valueOf(flightDto.getFlightFieldContent()))
                .build();
        return flight;
    }

}
