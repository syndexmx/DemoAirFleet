package com.github.syndexmx.demoairfleet.services.impl;


import com.github.syndexmx.demoairfleet.domain.Flight;
import com.github.syndexmx.demoairfleet.repository.entities.FlightEntity;
import com.github.syndexmx.demoairfleet.repository.mappers.FlightEntityMapper;
import com.github.syndexmx.demoairfleet.repository.repositories.FlightRepository;
import com.github.syndexmx.demoairfleet.services.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@Slf4j
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final FlightEntityMapper flightEntityMapper;

    @Autowired
    private FlightServiceImpl(FlightRepository flightRepository, FlightEntityMapper flightEntityMapper) {
        this.flightRepository = flightRepository;
        this.flightEntityMapper = flightEntityMapper;
    }

    @Override
    public Flight create(Flight flight) {
        UUID spoofId = UUID.randomUUID();
        flight.setId(spoofId);
        final FlightEntity savedEntity = flightRepository.save(flightEntityMapper
                .flightToFlightEntity(flight));
        final Flight savedFlight = flightEntityMapper.flightEntityToFlight(savedEntity);
        return savedFlight;
    }

    @Override
    public Flight save(Flight flight) {
        final FlightEntity savedEntity = flightRepository.save(flightEntityMapper
                .flightToFlightEntity(flight));
        final Flight savedFlight = flightEntityMapper.flightEntityToFlight(savedEntity);
        return savedFlight;
    }

    @Override
    public Optional<Flight> findById(String flightId) {
        final Optional<FlightEntity> flightEntityFound = flightRepository
                .findById(UUID.fromString(flightId));
        final Optional<Flight> flightFound = flightEntityFound.map(flightEntity ->
                flightEntityMapper.flightEntityToFlight(flightEntity));
        return flightFound;
    }

    @Override
    public List<Flight> listAll() {
        final List<FlightEntity> listOfFoundFlightEntities = flightRepository.findAll();
        final List<Flight> listOfFoundFlights =listOfFoundFlightEntities.stream()
                .map(entity -> flightEntityMapper.flightEntityToFlight(entity)).toList();
        return listOfFoundFlights;
    }

    @Override
    public boolean isPresent(String flightId) {
        return flightRepository.existsById(UUID.fromString(flightId));
    }

    @Override
    public boolean isPresent(Flight flight) {
        return flightRepository.existsById(flight.getId());
    }

    @Override
    public void deleteById(String flightId) {
        try {
            flightRepository.deleteById(UUID.fromString(flightId));
        } catch (final EmptyResultDataAccessException e) {
            log.debug("Attempted to delete non-existent flight");
        }
    }

}
