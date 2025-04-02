package com.github.syndexmx.demoairfleet.controllers;


import com.github.syndexmx.demoairfleet.domain.EngineType;
import com.github.syndexmx.demoairfleet.controllers.dtos.EngineTypeDto;
import com.github.syndexmx.demoairfleet.services.EngineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.github.syndexmx.demoairfleet.controllers.mappers.EngineTypeDtoMapper.*;


@RestController
public class EngineTypeController {

    private final String ROOT_API_PATH = "/api/v0/enginetypes";

    private final EngineTypeService enginetypeService;

    @Autowired
    private EngineTypeController(EngineTypeService enginetypeService) {
        this.enginetypeService = enginetypeService;
    }

    @PostMapping(ROOT_API_PATH)
    public ResponseEntity<EngineTypeDto> create(@RequestBody final EngineTypeDto enginetypeDto) {
        final EngineType engineType = enginetypeDtoNoIdToEngineType(enginetypeDto);
        final ResponseEntity<EngineTypeDto> responseEntity = new ResponseEntity<> (
                enginetypeToEngineTypeDto(enginetypeService.create(engineType)), HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping(ROOT_API_PATH +"/{enginetypeId}")
    public ResponseEntity<EngineTypeDto> retrieve(@PathVariable String enginetypeId) {
        final Optional<EngineType> foundEngineType = enginetypeService.findById(enginetypeId);
        if (foundEngineType.isEmpty()) {
            return new ResponseEntity<EngineTypeDto>(HttpStatus.NOT_FOUND);
        } else {
            final EngineTypeDto enginetypeDto = enginetypeToEngineTypeDto(foundEngineType.get());
            return new ResponseEntity<EngineTypeDto>(enginetypeDto, HttpStatus.FOUND);
        }
    }

    @GetMapping(ROOT_API_PATH)
    public ResponseEntity<List<EngineTypeDto>> retrieveAll() {
        final List<EngineType> listFound = enginetypeService.listAll();
        final List<EngineTypeDto> listFoundDtos = listFound.stream()
                .map(enginetype -> enginetypeToEngineTypeDto(enginetype)).toList();
        final ResponseEntity<List<EngineTypeDto>> response = new ResponseEntity<>(listFoundDtos,
                HttpStatus.OK);
        return response;
    }

    @PutMapping(ROOT_API_PATH +"/{enginetypeId}")
    public ResponseEntity<EngineTypeDto> update(@RequestBody final EngineTypeDto enginetypeDto) {
        final EngineType engineType = enginetypeDtoToEngineType(enginetypeDto);
        if (!enginetypeService.isPresent(engineType)) {
            final ResponseEntity<EngineTypeDto> responseEntity = new ResponseEntity<> (
                    enginetypeToEngineTypeDto(enginetypeService.save(engineType)), HttpStatus.CREATED);
            return responseEntity;
        }
        final ResponseEntity<EngineTypeDto> responseEntity = new ResponseEntity<> (
                enginetypeToEngineTypeDto(enginetypeService.save(engineType)), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping(ROOT_API_PATH +"/{enginetypeId}")
    public ResponseEntity deleteById(@PathVariable String enginetypeId) {
        enginetypeService.deleteById(enginetypeId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
