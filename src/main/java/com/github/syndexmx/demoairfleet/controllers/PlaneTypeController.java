package com.github.syndexmx.demoairfleet.controllers;


import com.github.syndexmx.demoairfleet.domain.PlaneType;
import com.github.syndexmx.demoairfleet.controllers.dtos.PlaneTypeDto;
import com.github.syndexmx.demoairfleet.services.PlaneTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.github.syndexmx.demoairfleet.controllers.mappers.PlaneTypeDtoMapper.*;


@RestController
public class PlaneTypeController {

    private final String ROOT_API_PATH = "/api/v0/planetypes";

    private final PlaneTypeService planetypeService;

    @Autowired
    private PlaneTypeController(PlaneTypeService planetypeService) {
        this.planetypeService = planetypeService;
    }

    @PostMapping(ROOT_API_PATH)
    public ResponseEntity<PlaneTypeDto> create(@RequestBody final PlaneTypeDto planetypeDto) {
        final PlaneType planeType = planetypeDtoNoIdToPlaneType(planetypeDto);
        final ResponseEntity<PlaneTypeDto> responseEntity = new ResponseEntity<> (
                planetypeToPlaneTypeDto(planetypeService.create(planeType)), HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping(ROOT_API_PATH +"/{planetypeId}")
    public ResponseEntity<PlaneTypeDto> retrieve(@PathVariable String planetypeId) {
        final Optional<PlaneType> foundPlaneType = planetypeService.findById(planetypeId);
        if (foundPlaneType.isEmpty()) {
            return new ResponseEntity<PlaneTypeDto>(HttpStatus.NOT_FOUND);
        } else {
            final PlaneTypeDto planetypeDto = planetypeToPlaneTypeDto(foundPlaneType.get());
            return new ResponseEntity<PlaneTypeDto>(planetypeDto, HttpStatus.FOUND);
        }
    }

    @GetMapping(ROOT_API_PATH)
    public ResponseEntity<List<PlaneTypeDto>> retrieveAll() {
        final List<PlaneType> listFound = planetypeService.listAll();
        final List<PlaneTypeDto> listFoundDtos = listFound.stream()
                .map(planetype -> planetypeToPlaneTypeDto(planetype)).toList();
        final ResponseEntity<List<PlaneTypeDto>> response = new ResponseEntity<>(listFoundDtos,
                HttpStatus.OK);
        return response;
    }

    @PutMapping(ROOT_API_PATH +"/{planetypeId}")
    public ResponseEntity<PlaneTypeDto> update(@RequestBody final PlaneTypeDto planetypeDto) {
        final PlaneType planeType = planetypeDtoToPlaneType(planetypeDto);
        if (!planetypeService.isPresent(planeType)) {
            final ResponseEntity<PlaneTypeDto> responseEntity = new ResponseEntity<> (
                    planetypeToPlaneTypeDto(planetypeService.save(planeType)), HttpStatus.CREATED);
            return responseEntity;
        }
        final ResponseEntity<PlaneTypeDto> responseEntity = new ResponseEntity<> (
                planetypeToPlaneTypeDto(planetypeService.save(planeType)), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping(ROOT_API_PATH +"/{planetypeId}")
    public ResponseEntity deleteById(@PathVariable String planetypeId) {
        planetypeService.deleteById(planetypeId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
