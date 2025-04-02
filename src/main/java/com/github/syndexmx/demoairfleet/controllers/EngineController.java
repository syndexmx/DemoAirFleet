package com.github.syndexmx.demoairfleet.controllers;


import com.github.syndexmx.demoairfleet.domain.Engine;
import com.github.syndexmx.demoairfleet.controllers.dtos.EngineDto;
import com.github.syndexmx.demoairfleet.services.EngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.github.syndexmx.demoairfleet.controllers.mappers.EngineDtoMapper.*;


@RestController
public class EngineController {

    private final String ROOT_API_PATH = "/api/v0/engines";

    private final EngineService engineService;

    @Autowired
    private EngineController(EngineService engineService) {
        this.engineService = engineService;
    }

    @PostMapping(ROOT_API_PATH)
    public ResponseEntity<EngineDto> create(@RequestBody final EngineDto engineDto) {
        final Engine engine = engineDtoNoIdToEngine(engineDto);
        final ResponseEntity<EngineDto> responseEntity = new ResponseEntity<> (
                engineToEngineDto(engineService.create(engine)), HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping(ROOT_API_PATH +"/{engineId}")
    public ResponseEntity<EngineDto> retrieve(@PathVariable String engineId) {
        final Optional<Engine> foundEngine = engineService.findById(engineId);
        if (foundEngine.isEmpty()) {
            return new ResponseEntity<EngineDto>(HttpStatus.NOT_FOUND);
        } else {
            final EngineDto engineDto = engineToEngineDto(foundEngine.get());
            return new ResponseEntity<EngineDto>(engineDto, HttpStatus.FOUND);
        }
    }

    @GetMapping(ROOT_API_PATH)
    public ResponseEntity<List<EngineDto>> retrieveAll() {
        final List<Engine> listFound = engineService.listAll();
        final List<EngineDto> listFoundDtos = listFound.stream()
                .map(engine -> engineToEngineDto(engine)).toList();
        final ResponseEntity<List<EngineDto>> response = new ResponseEntity<>(listFoundDtos,
                HttpStatus.OK);
        return response;
    }

    @PutMapping(ROOT_API_PATH +"/{engineId}")
    public ResponseEntity<EngineDto> update(@RequestBody final EngineDto engineDto) {
        final Engine engine = engineDtoToEngine(engineDto);
        if (!engineService.isPresent(engine)) {
            final ResponseEntity<EngineDto> responseEntity = new ResponseEntity<> (
                    engineToEngineDto(engineService.save(engine)), HttpStatus.CREATED);
            return responseEntity;
        }
        final ResponseEntity<EngineDto> responseEntity = new ResponseEntity<> (
                engineToEngineDto(engineService.save(engine)), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping(ROOT_API_PATH +"/{engineId}")
    public ResponseEntity deleteById(@PathVariable String engineId) {
        engineService.deleteById(engineId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
