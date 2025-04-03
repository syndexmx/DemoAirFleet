package com.github.syndexmx.demoairfleet.repository.mappers;

import com.github.syndexmx.demoairfleet.domain.Flight;
import com.github.syndexmx.demoairfleet.repository.entities.FlightEntity;
import org.springframework.stereotype.Component;

@Component
public class FlightEntityMapper {

    public FlightEntity flightToFlightEntity(Flight flight) {
        final FlightEntity flightEntity = FlightEntity.builder()
                .flightId(flight.getId())
                .name(flight.getName())
                .route(flight.getRoute())
                .pax(flight.getPax())
                .callsign(flight.getCallsign())
                .date(flight.getDate())
                .build();
        return flightEntity;
    }

    public Flight flightEntityToFlight(FlightEntity flightEntity) {
        Flight flight = Flight.builder()
                .id(flightEntity.getFlightId())
                .name(flightEntity.getName())
                .route(flightEntity.getRoute())
                .pax(flightEntity.getPax())
                .callsign(flightEntity.getCallsign())
                .date(flightEntity.getDate())
                .build();
        return flight;
    }



}
