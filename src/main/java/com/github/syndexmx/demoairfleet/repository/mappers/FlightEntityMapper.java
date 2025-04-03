package com.github.syndexmx.demoairfleet.repository.mappers;

import com.github.syndexmx.demoairfleet.domain.Flight;
import com.github.syndexmx.demoairfleet.repository.entities.FlightEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlightEntityMapper {

    private PilotEntityMapper pilotEntityMapper;
    private AircraftEntityMapper aircraftEntityMapper;

    @Autowired
    public FlightEntityMapper(PilotEntityMapper pilotEntityMapper, AircraftEntityMapper aircraftEntityMapper) {
        this.pilotEntityMapper = pilotEntityMapper;
        this.aircraftEntityMapper = aircraftEntityMapper;
    }

    public FlightEntity flightToFlightEntity(Flight flight) {
        final FlightEntity flightEntity = FlightEntity.builder()
                .id(flight.getId())
                .name(flight.getName())
                .route(flight.getRoute())
                .pax(flight.getPax())
                .callsign(flight.getCallsign())
                .date(flight.getDate())
                .aircraft(aircraftEntityMapper.aircraftToAircraftEntity(flight.getAircraft()))
                .captain(pilotEntityMapper.pilotToPilotEntity(flight.getCaptain()))
                .firstOfficer(pilotEntityMapper.pilotToPilotEntity(flight.getFirstOfficer()))
                .build();
        return flightEntity;
    }

    public Flight flightEntityToFlight(FlightEntity flightEntity) {
        Flight flight = Flight.builder()
                .id(flightEntity.getId())
                .name(flightEntity.getName())
                .route(flightEntity.getRoute())
                .pax(flightEntity.getPax())
                .callsign(flightEntity.getCallsign())
                .date(flightEntity.getDate())
                .aircraft(aircraftEntityMapper.aircraftEntityToAircraft(flightEntity.getAircraft()))
                .captain(pilotEntityMapper.pilotEntityToPilot(flightEntity.getCaptain()))
                .firstOfficer(pilotEntityMapper.pilotEntityToPilot(flightEntity.getFirstOfficer()))
                .build();
        return flight;
    }



}
