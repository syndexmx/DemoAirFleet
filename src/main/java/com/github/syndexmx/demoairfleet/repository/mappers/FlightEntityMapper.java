package com.github.syndexmx.demoairfleet.repository.mappers;


import com.github.syndexmx.demoairfleet.domain.FlightData;
import com.github.syndexmx.demoairfleet.domain.Flight;
import com.github.syndexmx.demoairfleet.repository.entities.FlightEntity;
import org.springframework.stereotype.Component;


@Component
public class FlightEntityMapper {

    public FlightEntity flightToFlightEntity(Flight flight) {
        final FlightEntity flightEntity = FlightEntity.builder()
                .flightId(flight.getId())
                .flightFieldContent(flight.getFlightData().toString())
                .build();
        return flightEntity;
    }

    public Flight flightEntityToFlight(FlightEntity flightEntity) {
        Flight flight = Flight.builder()
                .id(flightEntity.getFlightId())
                .flightData(FlightData.valueOf(flightEntity.getFlightFieldContent()))
                .build();
        return flight;
    }



}
