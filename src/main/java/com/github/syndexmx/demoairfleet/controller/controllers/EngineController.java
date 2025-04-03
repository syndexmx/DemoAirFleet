package com.github.syndexmx.demoairfleet.controller.controllers;

import com.github.syndexmx.demoairfleet.controller.mappers.EngineDtoMapper;
import com.github.syndexmx.demoairfleet.domain.Engine;
import com.github.syndexmx.demoairfleet.controller.dtos.EngineDto;
import com.github.syndexmx.demoairfleet.exceptions.AirfleetIncorrectIdApiRequestException;
import com.github.syndexmx.demoairfleet.services.EngineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class EngineController {

    private final String ROOT_API_PATH = "/api/v0/engines";

    private final EngineService engineService;
    private final EngineDtoMapper engineDtoMapper;

    @Autowired
    private EngineController(EngineService engineService, EngineDtoMapper engineDtoMapper) {
        this.engineService = engineService;
        this.engineDtoMapper = engineDtoMapper;
    }

    @PostMapping(ROOT_API_PATH)
    public ResponseEntity<EngineDto> create(@RequestBody final EngineDto engineDto) {
        log.info("POST " + ROOT_API_PATH + " " + engineDto.toString());
        final Engine engine = engineDtoMapper.engineDtoNoIdToengine(engineDto);
        final ResponseEntity<EngineDto> responseEntity = new ResponseEntity<> (
                engineDtoMapper.engineToEngineDto(engineService.create(engine)), HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping(ROOT_API_PATH +"/{engineId}")
    public ResponseEntity<EngineDto> retrieve(@PathVariable Long engineId) {
        final Optional<Engine> foundengine = engineService.findById(engineId);
        if (foundengine.isEmpty()) {
            return new ResponseEntity<EngineDto>(HttpStatus.NOT_FOUND);
        } else {
            final EngineDto engineDto = engineDtoMapper.engineToEngineDto(foundengine.get());
            return new ResponseEntity<EngineDto>(engineDto, HttpStatus.FOUND);
        }
    }

    @GetMapping(ROOT_API_PATH)
    public ResponseEntity<List<EngineDto>> retrieveAll() {
        final List<Engine> listFound = engineService.listAll();
        final List<EngineDto> listFoundDtos = listFound.stream()
                .map(engine -> engineDtoMapper.engineToEngineDto(engine)).toList();
        final ResponseEntity<List<EngineDto>> response = new ResponseEntity<>(listFoundDtos,
                HttpStatus.OK);
        return response;
    }

    @PutMapping(ROOT_API_PATH +"/{engineId}")
    public ResponseEntity<EngineDto> update(@PathVariable String engineId,
                                            @RequestBody final EngineDto engineDto) {
        log.info("PUT " + ROOT_API_PATH + "/" + engineId + engineDto.toString());
        if (Long.parseLong(engineId) != (long)engineDto.getId()) {
            throw new AirfleetIncorrectIdApiRequestException("Path id and inner DTO id should not differ.");
        }
        final Engine engine = engineDtoMapper.engineDtoToEngine(engineDto);
        if (!engineService.isPresent(engine)) {
            final ResponseEntity<EngineDto> responseEntity = new ResponseEntity<> (
                    engineDtoMapper.engineToEngineDto(engineService.save(engine)), HttpStatus.CREATED);
            return responseEntity;
        }
        final ResponseEntity<EngineDto> responseEntity = new ResponseEntity<> (
                engineDtoMapper.engineToEngineDto(engineService.save(engine)), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping(ROOT_API_PATH +"/{engineId}")
    public ResponseEntity deleteById(@PathVariable Long engineId) {
        log.info("DELETE " + ROOT_API_PATH + "/" + engineId);
        engineService.deleteById(engineId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
