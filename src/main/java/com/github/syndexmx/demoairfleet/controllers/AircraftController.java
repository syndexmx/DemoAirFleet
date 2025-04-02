package com.github.syndexmx.demoairfleet.controllers;


import com.github.syndexmx.demoairfleet.domain.Aircraft;
import com.github.syndexmx.demoairfleet.controllers.dtos.AircraftDto;
import com.github.syndexmx.demoairfleet.services.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.github.syndexmx.demoairfleet.controllers.mappers.AircraftDtoMapper.*;


@RestController
public class AircraftController {

    private final String ROOT_API_PATH = "/api/v0/aircrafts";

    private final AircraftService aircraftService;

    @Autowired
    private AircraftController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    @PostMapping(ROOT_API_PATH)
    public ResponseEntity<AircraftDto> create(@RequestBody final AircraftDto aircraftDto) {
        final Aircraft aircraft = aircraftDtoNoIdToAircraft(aircraftDto);
        final ResponseEntity<AircraftDto> responseEntity = new ResponseEntity<> (
                aircraftToAircraftDto(aircraftService.create(aircraft)), HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping(ROOT_API_PATH +"/{aircraftId}")
    public ResponseEntity<AircraftDto> retrieve(@PathVariable String aircraftId) {
        final Optional<Aircraft> foundAircraft = aircraftService.findById(aircraftId);
        if (foundAircraft.isEmpty()) {
            return new ResponseEntity<AircraftDto>(HttpStatus.NOT_FOUND);
        } else {
            final AircraftDto aircraftDto = aircraftToAircraftDto(foundAircraft.get());
            return new ResponseEntity<AircraftDto>(aircraftDto, HttpStatus.FOUND);
        }
    }

    @GetMapping(ROOT_API_PATH)
    public ResponseEntity<List<AircraftDto>> retrieveAll() {
        final List<Aircraft> listFound = aircraftService.listAll();
        final List<AircraftDto> listFoundDtos = listFound.stream()
                .map(aircraft -> aircraftToAircraftDto(aircraft)).toList();
        final ResponseEntity<List<AircraftDto>> response = new ResponseEntity<>(listFoundDtos,
                HttpStatus.OK);
        return response;
    }

    @PutMapping(ROOT_API_PATH +"/{aircraftId}")
    public ResponseEntity<AircraftDto> update(@RequestBody final AircraftDto aircraftDto) {
        final Aircraft aircraft = aircraftDtoToAircraft(aircraftDto);
        if (!aircraftService.isPresent(aircraft)) {
            final ResponseEntity<AircraftDto> responseEntity = new ResponseEntity<> (
                    aircraftToAircraftDto(aircraftService.save(aircraft)), HttpStatus.CREATED);
            return responseEntity;
        }
        final ResponseEntity<AircraftDto> responseEntity = new ResponseEntity<> (
                aircraftToAircraftDto(aircraftService.save(aircraft)), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping(ROOT_API_PATH +"/{aircraftId}")
    public ResponseEntity deleteById(@PathVariable String aircraftId) {
        aircraftService.deleteById(aircraftId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
