package com.github.syndexmx.demoairfleet.services.impl;


import com.github.syndexmx.demoairfleet.domain.Aircraft;
import com.github.syndexmx.demoairfleet.repositories.entities.AircraftEntity;
import com.github.syndexmx.demoairfleet.repositories.mappers.AircraftEntityMapper;
import com.github.syndexmx.demoairfleet.repositories.AircraftRepository;
import com.github.syndexmx.demoairfleet.services.AircraftService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@Slf4j
public class AircraftServiceImpl implements AircraftService {

    private final AircraftRepository aircraftRepository;
    private final AircraftEntityMapper aircraftEntityMapper;

    @Autowired
    private AircraftServiceImpl(AircraftRepository aircraftRepository, AircraftEntityMapper aircraftEntityMapper) {
        this.aircraftRepository = aircraftRepository;
        this.aircraftEntityMapper = aircraftEntityMapper;
    }

    @Override
    public Aircraft create(Aircraft aircraft) {
        UUID spoofId = UUID.randomUUID();
        aircraft.setId(spoofId);
        final AircraftEntity savedEntity = aircraftRepository.save(aircraftEntityMapper
                .aircraftToAircraftEntity(aircraft));
        final Aircraft savedAircraft = aircraftEntityMapper.aircraftEntityToAircraft(savedEntity);
        return savedAircraft;
    }

    @Override
    public Aircraft save(Aircraft aircraft) {
        final AircraftEntity savedEntity = aircraftRepository.save(aircraftEntityMapper
                .aircraftToAircraftEntity(aircraft));
        final Aircraft savedAircraft = aircraftEntityMapper.aircraftEntityToAircraft(savedEntity);
        return savedAircraft;
    }

    @Override
    public Optional<Aircraft> findById(String aircraftId) {
        final Optional<AircraftEntity> aircraftEntityFound = aircraftRepository
                .findById(UUID.fromString(aircraftId));
        final Optional<Aircraft> aircraftFound = aircraftEntityFound.map(aircraftEntity ->
                aircraftEntityMapper.aircraftEntityToAircraft(aircraftEntity));
        return aircraftFound;
    }

    @Override
    public List<Aircraft> listAll() {
        final List<AircraftEntity> listOfFoundAircraftEntities = aircraftRepository.findAll();
        final List<Aircraft> listOfFoundAircrafts =listOfFoundAircraftEntities.stream()
                .map(entity -> aircraftEntityMapper.aircraftEntityToAircraft(entity)).toList();
        return listOfFoundAircrafts;
    }

    @Override
    public boolean isPresent(String aircraftId) {
        return aircraftRepository.existsById(UUID.fromString(aircraftId));
    }

    @Override
    public boolean isPresent(Aircraft aircraft) {
        return aircraftRepository.existsById(aircraft.getId());
    }

    @Override
    public void deleteById(String aircraftId) {
        try {
            aircraftRepository.deleteById(UUID.fromString(aircraftId));
        } catch (final EmptyResultDataAccessException e) {
            log.debug("Attempted to delete non-existent aircraft");
        }
    }

}
