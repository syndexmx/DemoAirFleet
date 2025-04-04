package com.github.syndexmx.demoairfleet.repository.mappers;

import com.github.syndexmx.demoairfleet.domain.Flight;
import com.github.syndexmx.demoairfleet.repository.entities.FlightEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlightEntityMapper {

    public static FlightEntity flightToFlightEntity(Flight flight) {
        final FlightEntity flightEntity = FlightEntity.builder()
                .id(flight.getId())
                .name(flight.getName())
                .route(flight.getRoute())
                .pax(flight.getPax())
                .callsign(flight.getCallsign())
                .date(flight.getDate())
                .aircraft(AircraftEntityMapper.aircraftToAircraftEntity(flight.getAircraft()))
                .captain(PilotEntityMapper.pilotToPilotEntity(flight.getCaptain()))
                .firstOfficer(PilotEntityMapper.pilotToPilotEntity(flight.getFirstOfficer()))
                .build();
        return flightEntity;
    }

    public static Flight flightEntityToFlight(FlightEntity flightEntity) {
        Flight flight = Flight.builder()
                .id(flightEntity.getId())
                .name(flightEntity.getName())
                .route(flightEntity.getRoute())
                .pax(flightEntity.getPax())
                .callsign(flightEntity.getCallsign())
                .date(flightEntity.getDate())
                .aircraft(AircraftEntityMapper.aircraftEntityToAircraft(flightEntity.getAircraft()))
                .captain(PilotEntityMapper.pilotEntityToPilot(flightEntity.getCaptain()))
                .firstOfficer(PilotEntityMapper.pilotEntityToPilot(flightEntity.getFirstOfficer()))
                .build();
        return flight;
    }



}
