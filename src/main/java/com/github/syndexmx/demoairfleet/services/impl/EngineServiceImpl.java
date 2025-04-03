package com.github.syndexmx.demoairfleet.services.impl;

import com.github.syndexmx.demoairfleet.domain.Engine;
import com.github.syndexmx.demoairfleet.repository.entities.EngineEntity;
import com.github.syndexmx.demoairfleet.repository.mappers.EngineEntityMapper;
import com.github.syndexmx.demoairfleet.repository.repositories.EngineRepository;
import com.github.syndexmx.demoairfleet.services.EngineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        engine.setId(null);
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
    public Optional<Engine> findById(Long engineId) {
        final Optional<EngineEntity> engineEntityFound = engineRepository
                .findById(engineId);
        final Optional<Engine> engineFound = engineEntityFound.map(engineEntity ->
                engineEntityMapper.engineEntityToEngine(engineEntity));
        return engineFound;
    }

    @Override
    public List<Engine> listAll() {
        final List<EngineEntity> listOfFoundengineEntities = engineRepository.findAll();
        final List<Engine> listOfFoundEngines =listOfFoundengineEntities.stream()
                .map(entity -> engineEntityMapper.engineEntityToEngine(entity)).toList();
        return listOfFoundEngines;
    }

    @Override
    public boolean isPresent(Long engineId) {
        return engineRepository.existsById(engineId);
    }

    @Override
    public boolean isPresent(Engine engine) {
        return engineRepository.existsById(engine.getId());
    }

    @Override
    public void deleteById(Long engineId) {
        try {
            engineRepository.deleteById(engineId);
        } catch (final EmptyResultDataAccessException e) {
            log.debug("Attempted to delete non-existent engine");
        }
    }

}
