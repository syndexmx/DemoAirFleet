package com.github.syndexmx.demoairfleet.services.impl;


import com.github.syndexmx.demoairfleet.domain.Aircraft;
import com.github.syndexmx.demoairfleet.repository.entities.AircraftEntity;
import com.github.syndexmx.demoairfleet.repository.mappers.AircraftEntityMapper;
import com.github.syndexmx.demoairfleet.repository.repositories.AircraftRepository;
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
        aircraft.setId(null);
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
    public Optional<Aircraft> findById(Long aircraftId) {
        final Optional<AircraftEntity> aircraftEntityFound = aircraftRepository
                .findById(aircraftId);
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
    public boolean isPresent(Long aircraftId) {
        return aircraftRepository.existsById(aircraftId);
    }

    @Override
    public boolean isPresent(Aircraft aircraft) {
        return aircraftRepository.existsById(aircraft.getId());
    }

    @Override
    public void deleteById(Long aircraftId) {
        try {
            aircraftRepository.deleteById(aircraftId);
        } catch (final EmptyResultDataAccessException e) {
            log.debug("Attempted to delete non-existent aircraft");
        }
    }

}
