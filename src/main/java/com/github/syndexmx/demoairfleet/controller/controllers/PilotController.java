package com.github.syndexmx.demoairfleet.controller.controllers;


import com.github.syndexmx.demoairfleet.controller.mappers.PilotDtoMapper;
import com.github.syndexmx.demoairfleet.domain.Pilot;
import com.github.syndexmx.demoairfleet.controller.dtos.PilotDto;
import com.github.syndexmx.demoairfleet.services.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class PilotController {

    private final String ROOT_API_PATH = "/api/v0/pilots";

    private final PilotService pilotService;
    private final PilotDtoMapper pilotDtoMapper;

    @Autowired
    private PilotController(PilotService pilotService, PilotDtoMapper pilotDtoMapper) {
        this.pilotService = pilotService;
        this.pilotDtoMapper = pilotDtoMapper;
    }

    @PostMapping(ROOT_API_PATH)
    public ResponseEntity<PilotDto> create(@RequestBody final PilotDto pilotDto) {
        final Pilot pilot = pilotDtoMapper.pilotDtoNoIdToPilot(pilotDto);
        final ResponseEntity<PilotDto> responseEntity = new ResponseEntity<> (
                pilotDtoMapper.pilotToPilotDto(pilotService.create(pilot)), HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping(ROOT_API_PATH +"/{pilotId}")
    public ResponseEntity<PilotDto> retrieve(@PathVariable String pilotId) {
        final Optional<Pilot> foundPilot = pilotService.findById(pilotId);
        if (foundPilot.isEmpty()) {
            return new ResponseEntity<PilotDto>(HttpStatus.NOT_FOUND);
        } else {
            final PilotDto pilotDto = pilotDtoMapper.pilotToPilotDto(foundPilot.get());
            return new ResponseEntity<PilotDto>(pilotDto, HttpStatus.FOUND);
        }
    }

    @GetMapping(ROOT_API_PATH)
    public ResponseEntity<List<PilotDto>> retrieveAll() {
        final List<Pilot> listFound = pilotService.listAll();
        final List<PilotDto> listFoundDtos = listFound.stream()
                .map(pilot -> pilotDtoMapper.pilotToPilotDto(pilot)).toList();
        final ResponseEntity<List<PilotDto>> response = new ResponseEntity<>(listFoundDtos,
                HttpStatus.OK);
        return response;
    }

    @PutMapping(ROOT_API_PATH +"/{pilotId}")
    public ResponseEntity<PilotDto> update(@RequestBody final PilotDto pilotDto) {
        final Pilot pilot = pilotDtoMapper.pilotDtoToPilot(pilotDto);
        if (!pilotService.isPresent(pilot)) {
            final ResponseEntity<PilotDto> responseEntity = new ResponseEntity<> (
                    pilotDtoMapper.pilotToPilotDto(pilotService.save(pilot)), HttpStatus.CREATED);
            return responseEntity;
        }
        final ResponseEntity<PilotDto> responseEntity = new ResponseEntity<> (
                pilotDtoMapper.pilotToPilotDto(pilotService.save(pilot)), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping(ROOT_API_PATH +"/{pilotId}")
    public ResponseEntity deleteById(@PathVariable String pilotId) {
        pilotService.deleteById(pilotId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
