package com.github.syndexmx.demoairfleet.services.impl;


import com.github.syndexmx.demoairfleet.domain.Engine;
import com.github.syndexmx.demoairfleet.repositories.entities.EngineEntity;
import com.github.syndexmx.demoairfleet.repositories.mappers.EngineEntityMapper;
import com.github.syndexmx.demoairfleet.repositories.EngineRepository;
import com.github.syndexmx.demoairfleet.services.EngineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@Slf4j
public class EngineServiceImpl implements EngineService {

    private final EngineRepository engineRepository;
    private final EngineEntityMapper engineEntityMapper;

    @Autowired
    private EngineServiceImpl(EngineRepository engineRepository, EngineEntityMapper engineEntityMapper) {
        this.engineRepository = engineRepository;
        this.engineEntityMapper = engineEntityMapper;
    }

    @Override
    public Engine create(Engine engine) {
        UUID spoofId = UUID.randomUUID();
        engine.setId(spoofId);
        final EngineEntity savedEntity = engineRepository.save(engineEntityMapper
                .engineToEngineEntity(engine));
        final Engine savedEngine = engineEntityMapper.engineEntityToEngine(savedEntity);
        return savedEngine;
    }

    @Override
    public Engine save(Engine engine) {
        final EngineEntity savedEntity = engineRepository.save(engineEntityMapper
                .engineToEngineEntity(engine));
        final Engine savedEngine = engineEntityMapper.engineEntityToEngine(savedEntity);
        return savedEngine;
    }

    @Override
    public Optional<Engine> findById(String engineId) {
        final Optional<EngineEntity> engineEntityFound = engineRepository
                .findById(UUID.fromString(engineId));
        final Optional<Engine> engineFound = engineEntityFound.map(engineEntity ->
                engineEntityMapper.engineEntityToEngine(engineEntity));
        return engineFound;
    }

    @Override
    public List<Engine> listAll() {
        final List<EngineEntity> listOfFoundEngineEntities = engineRepository.findAll();
        final List<Engine> listOfFoundEngines =listOfFoundEngineEntities.stream()
                .map(entity -> engineEntityMapper.engineEntityToEngine(entity)).toList();
        return listOfFoundEngines;
    }

    @Override
    public boolean isPresent(String engineId) {
        return engineRepository.existsById(UUID.fromString(engineId));
    }

    @Override
    public boolean isPresent(Engine engine) {
        return engineRepository.existsById(engine.getId());
    }

    @Override
    public void deleteById(String engineId) {
        try {
            engineRepository.deleteById(UUID.fromString(engineId));
        } catch (final EmptyResultDataAccessException e) {
            log.debug("Attempted to delete non-existent engine");
        }
    }

}
