package com.github.syndexmx.demoairfleet.services.impl;


import com.github.syndexmx.demoairfleet.domain.PlaneType;
import com.github.syndexmx.demoairfleet.repositories.entities.PlaneTypeEntity;
import com.github.syndexmx.demoairfleet.repositories.mappers.PlaneTypeEntityMapper;
import com.github.syndexmx.demoairfleet.repositories.PlaneTypeRepository;
import com.github.syndexmx.demoairfleet.services.PlaneTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@Slf4j
public class PlaneTypeServiceImpl implements PlaneTypeService {

    private final PlaneTypeRepository planetypeRepository;
    private final PlaneTypeEntityMapper planetypeEntityMapper;

    @Autowired
    private PlaneTypeServiceImpl(PlaneTypeRepository planetypeRepository, PlaneTypeEntityMapper planetypeEntityMapper) {
        this.planetypeRepository = planetypeRepository;
        this.planetypeEntityMapper = planetypeEntityMapper;
    }

    @Override
    public PlaneType create(PlaneType planeType) {
        UUID spoofId = UUID.randomUUID();
        planeType.setId(spoofId);
        final PlaneTypeEntity savedEntity = planetypeRepository.save(planetypeEntityMapper
                .planetypeToPlaneTypeEntity(planeType));
        final PlaneType savedPlaneType = planetypeEntityMapper.planetypeEntityToPlaneType(savedEntity);
        return savedPlaneType;
    }

    @Override
    public PlaneType save(PlaneType planeType) {
        final PlaneTypeEntity savedEntity = planetypeRepository.save(planetypeEntityMapper
                .planetypeToPlaneTypeEntity(planeType));
        final PlaneType savedPlaneType = planetypeEntityMapper.planetypeEntityToPlaneType(savedEntity);
        return savedPlaneType;
    }

    @Override
    public Optional<PlaneType> findById(String planetypeId) {
        final Optional<PlaneTypeEntity> planetypeEntityFound = planetypeRepository
                .findById(UUID.fromString(planetypeId));
        final Optional<PlaneType> planetypeFound = planetypeEntityFound.map(planetypeEntity ->
                planetypeEntityMapper.planetypeEntityToPlaneType(planetypeEntity));
        return planetypeFound;
    }

    @Override
    public List<PlaneType> listAll() {
        final List<PlaneTypeEntity> listOfFoundPlaneTypeEntities = planetypeRepository.findAll();
        final List<PlaneType> listOfFoundPlaneTypes =listOfFoundPlaneTypeEntities.stream()
                .map(entity -> planetypeEntityMapper.planetypeEntityToPlaneType(entity)).toList();
        return listOfFoundPlaneTypes;
    }

    @Override
    public boolean isPresent(String planetypeId) {
        return planetypeRepository.existsById(UUID.fromString(planetypeId));
    }

    @Override
    public boolean isPresent(PlaneType planeType) {
        return planetypeRepository.existsById(planeType.getId());
    }

    @Override
    public void deleteById(String planetypeId) {
        try {
            planetypeRepository.deleteById(UUID.fromString(planetypeId));
        } catch (final EmptyResultDataAccessException e) {
            log.debug("Attempted to delete non-existent planetype");
        }
    }

}
