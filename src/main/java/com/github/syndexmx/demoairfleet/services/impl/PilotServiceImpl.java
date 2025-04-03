package com.github.syndexmx.demoairfleet.services.impl;


import com.github.syndexmx.demoairfleet.domain.Pilot;
import com.github.syndexmx.demoairfleet.repository.entities.PilotEntity;
import com.github.syndexmx.demoairfleet.repository.mappers.PilotEntityMapper;
import com.github.syndexmx.demoairfleet.repository.repositories.PilotRepository;
import com.github.syndexmx.demoairfleet.services.PilotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@Slf4j
public class PilotServiceImpl implements PilotService {

    private final PilotRepository pilotRepository;
    private final PilotEntityMapper pilotEntityMapper;

    @Autowired
    private PilotServiceImpl(PilotRepository pilotRepository, PilotEntityMapper pilotEntityMapper) {
        this.pilotRepository = pilotRepository;
        this.pilotEntityMapper = pilotEntityMapper;
    }

    @Override
    public Pilot create(Pilot pilot) {
        UUID spoofId = UUID.randomUUID();
        pilot.setId(spoofId);
        final PilotEntity savedEntity = pilotRepository.save(pilotEntityMapper
                .pilotToPilotEntity(pilot));
        final Pilot savedPilot = pilotEntityMapper.pilotEntityToPilot(savedEntity);
        return savedPilot;
    }

    @Override
    public Pilot save(Pilot pilot) {
        final PilotEntity savedEntity = pilotRepository.save(pilotEntityMapper
                .pilotToPilotEntity(pilot));
        final Pilot savedPilot = pilotEntityMapper.pilotEntityToPilot(savedEntity);
        return savedPilot;
    }

    @Override
    public Optional<Pilot> findById(String pilotId) {
        final Optional<PilotEntity> pilotEntityFound = pilotRepository
                .findById(UUID.fromString(pilotId));
        final Optional<Pilot> pilotFound = pilotEntityFound.map(pilotEntity ->
                pilotEntityMapper.pilotEntityToPilot(pilotEntity));
        return pilotFound;
    }

    @Override
    public List<Pilot> listAll() {
        final List<PilotEntity> listOfFoundPilotEntities = pilotRepository.findAll();
        final List<Pilot> listOfFoundPilots =listOfFoundPilotEntities.stream()
                .map(entity -> pilotEntityMapper.pilotEntityToPilot(entity)).toList();
        return listOfFoundPilots;
    }

    @Override
    public boolean isPresent(String pilotId) {
        return pilotRepository.existsById(UUID.fromString(pilotId));
    }

    @Override
    public boolean isPresent(Pilot pilot) {
        return pilotRepository.existsById(pilot.getId());
    }

    @Override
    public void deleteById(String pilotId) {
        try {
            pilotRepository.deleteById(UUID.fromString(pilotId));
        } catch (final EmptyResultDataAccessException e) {
            log.debug("Attempted to delete non-existent pilot");
        }
    }

}
