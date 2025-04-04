package com.github.syndexmx.demoairfleet.controller.controllers;

import com.github.syndexmx.demoairfleet.controller.mappers.AircraftDtoMapper;
import com.github.syndexmx.demoairfleet.domain.Aircraft;
import com.github.syndexmx.demoairfleet.controller.dtos.AircraftDto;
import com.github.syndexmx.demoairfleet.controller.exceptions.AirfleetIncorrectApiRequestException;
import com.github.syndexmx.demoairfleet.services.AircraftService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@Tag(name = "Aircrafts", description = "API Самолетов (бортов)")
public class AircraftController {

    private final String ROOT_API_PATH = "/api/v0/aircrafts";

    private final AircraftService aircraftService;
    private final AircraftDtoMapper aircraftDtoMapper;

    @Autowired
    private AircraftController(AircraftService aircraftService, AircraftDtoMapper aircraftDtoMapper) {
        this.aircraftService = aircraftService;
        this.aircraftDtoMapper = aircraftDtoMapper;
    }

    @PostMapping(ROOT_API_PATH)
    @Operation(summary = "Add an aircraft",
            description = "Добавление самолёта")
    public ResponseEntity<AircraftDto> create(@RequestBody final AircraftDto aircraftDto) {
        log.info("POST " + ROOT_API_PATH + " " + aircraftDto.toString());
        final Aircraft aircraft = aircraftDtoMapper.aircraftDtoNoIdToAircraft(aircraftDto);
        final ResponseEntity<AircraftDto> responseEntity = new ResponseEntity<> (
                aircraftDtoMapper.aircraftToAircraftDto(aircraftService.create(aircraft)), HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping(ROOT_API_PATH +"/{aircraftId}")
    @Operation(summary = "Get aircraft info",
            description = "Получить данные самолёта")
    public ResponseEntity<AircraftDto> retrieve(@PathVariable Long aircraftId) {
        final Optional<Aircraft> foundAircraft = aircraftService.findById(aircraftId);
        if (foundAircraft.isEmpty()) {
            return new ResponseEntity<AircraftDto>(HttpStatus.NOT_FOUND);
        } else {
            final AircraftDto aircraftDto = aircraftDtoMapper.aircraftToAircraftDto(foundAircraft.get());
            return new ResponseEntity<AircraftDto>(aircraftDto, HttpStatus.FOUND);
        }
    }

    @GetMapping(ROOT_API_PATH)
    @Operation(summary = "Get aircraft list",
            description = "Получить список самолётов")
    public ResponseEntity<List<AircraftDto>> retrieveAll() {
        final List<Aircraft> listFound = aircraftService.listAll();
        final List<AircraftDto> listFoundDtos = listFound.stream()
                .map(aircraft -> aircraftDtoMapper.aircraftToAircraftDto(aircraft)).toList();
        final ResponseEntity<List<AircraftDto>> response = new ResponseEntity<>(listFoundDtos,
                HttpStatus.OK);
        return response;
    }

    @PutMapping(ROOT_API_PATH +"/{aircraftId}")
    @Operation(summary = "Update/Correct aircraft info",
            description = "Записать обновленные данные самолёта")
    public ResponseEntity<AircraftDto> update(@PathVariable String aircraftId,
                                              @RequestBody final AircraftDto aircraftDto) {
        log.info("PUT " + ROOT_API_PATH + "/" + aircraftId + aircraftDto.toString());
        if (Long.parseLong(aircraftId) != (long)aircraftDto.getId()) {
            throw new AirfleetIncorrectApiRequestException("Path id and inner DTO id should not differ.");
        }
        final Aircraft aircraft = aircraftDtoMapper.aircraftDtoToAircraft(aircraftDto);
        if (!aircraftService.isPresent(aircraft)) {
            final ResponseEntity<AircraftDto> responseEntity = new ResponseEntity<> (
                    aircraftDtoMapper.aircraftToAircraftDto(aircraftService.save(aircraft)), HttpStatus.CREATED);
            return responseEntity;
        }
        final ResponseEntity<AircraftDto> responseEntity = new ResponseEntity<> (
                aircraftDtoMapper.aircraftToAircraftDto(aircraftService.save(aircraft)), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping(ROOT_API_PATH +"/{aircraftId}")
    @Operation(summary = "Delete an aircraft",
            description = "Удалить данные самолёта")
    public ResponseEntity deleteById(@PathVariable Long aircraftId) {
        log.info("DELETE " + ROOT_API_PATH + "/" + aircraftId);
        aircraftService.deleteById(aircraftId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
