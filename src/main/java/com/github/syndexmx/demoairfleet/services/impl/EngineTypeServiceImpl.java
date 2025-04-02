package com.github.syndexmx.demoairfleet.services.impl;


import com.github.syndexmx.demoairfleet.domain.EngineType;
import com.github.syndexmx.demoairfleet.repositories.entities.EngineTypeEntity;
import com.github.syndexmx.demoairfleet.repositories.mappers.EngineTypeEntityMapper;
import com.github.syndexmx.demoairfleet.repositories.EngineTypeRepository;
import com.github.syndexmx.demoairfleet.services.EngineTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@Slf4j
public class EngineTypeServiceImpl implements EngineTypeService {

    private final EngineTypeRepository enginetypeRepository;
    private final EngineTypeEntityMapper enginetypeEntityMapper;

    @Autowired
    private EngineTypeServiceImpl(EngineTypeRepository enginetypeRepository, EngineTypeEntityMapper enginetypeEntityMapper) {
        this.enginetypeRepository = enginetypeRepository;
        this.enginetypeEntityMapper = enginetypeEntityMapper;
    }

    @Override
    public EngineType create(EngineType engineType) {
        UUID spoofId = UUID.randomUUID();
        engineType.setId(spoofId);
        final EngineTypeEntity savedEntity = enginetypeRepository.save(enginetypeEntityMapper
                .enginetypeToEngineTypeEntity(engineType));
        final EngineType savedEngineType = enginetypeEntityMapper.enginetypeEntityToEngineType(savedEntity);
        return savedEngineType;
    }

    @Override
    public EngineType save(EngineType engineType) {
        final EngineTypeEntity savedEntity = enginetypeRepository.save(enginetypeEntityMapper
                .enginetypeToEngineTypeEntity(engineType));
        final EngineType savedEngineType = enginetypeEntityMapper.enginetypeEntityToEngineType(savedEntity);
        return savedEngineType;
    }

    @Override
    public Optional<EngineType> findById(String enginetypeId) {
        final Optional<EngineTypeEntity> enginetypeEntityFound = enginetypeRepository
                .findById(UUID.fromString(enginetypeId));
        final Optional<EngineType> enginetypeFound = enginetypeEntityFound.map(enginetypeEntity ->
                enginetypeEntityMapper.enginetypeEntityToEngineType(enginetypeEntity));
        return enginetypeFound;
    }

    @Override
    public List<EngineType> listAll() {
        final List<EngineTypeEntity> listOfFoundEngineTypeEntities = enginetypeRepository.findAll();
        final List<EngineType> listOfFoundEngineTypes =listOfFoundEngineTypeEntities.stream()
                .map(entity -> enginetypeEntityMapper.enginetypeEntityToEngineType(entity)).toList();
        return listOfFoundEngineTypes;
    }

    @Override
    public boolean isPresent(String enginetypeId) {
        return enginetypeRepository.existsById(UUID.fromString(enginetypeId));
    }

    @Override
    public boolean isPresent(EngineType engineType) {
        return enginetypeRepository.existsById(engineType.getId());
    }

    @Override
    public void deleteById(String enginetypeId) {
        try {
            enginetypeRepository.deleteById(UUID.fromString(enginetypeId));
        } catch (final EmptyResultDataAccessException e) {
            log.debug("Attempted to delete non-existent enginetype");
        }
    }

}
