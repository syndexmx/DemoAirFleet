package com.github.syndexmx.demoairfleet.controller.controllers;

import com.github.syndexmx.demoairfleet.controller.mappers.FlightDtoMapper;
import com.github.syndexmx.demoairfleet.domain.Flight;
import com.github.syndexmx.demoairfleet.controller.dtos.FlightDto;
import com.github.syndexmx.demoairfleet.controller.exceptions.AirfleetIncorrectApiRequestException;
import com.github.syndexmx.demoairfleet.services.FlightService;
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
@Tag(name = "Flights", description = "API Полётов")
public class FlightController {

    private final String ROOT_API_PATH = "/api/v0/flights";

    private final FlightService flightService;
    private final FlightDtoMapper flightDtoMapper;

    @Autowired
    private FlightController(FlightService flightService, FlightDtoMapper flightDtoMapper) {
        this.flightService = flightService;
        this.flightDtoMapper = flightDtoMapper;
    }

    @PostMapping(ROOT_API_PATH)
    @Operation(summary = "Add a flight",
            description = "Добавление полёта")
    public ResponseEntity<FlightDto> create(@RequestBody final FlightDto flightDto) {
        log.info("POST " + ROOT_API_PATH + " " + flightDto.toString());
        final Flight flight = flightDtoMapper.flightDtoNoIdToFlight(flightDto);
        final ResponseEntity<FlightDto> responseEntity = new ResponseEntity<> (
                flightDtoMapper.flightToFlightDto(flightService.create(flight)), HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping(ROOT_API_PATH +"/{flightId}")
    @Operation(summary = "Get flight info",
            description = "Получить данные полёта")
    public ResponseEntity<FlightDto> retrieve(@PathVariable String flightId) {
        final Optional<Flight> foundFlight = flightService.findById(flightId);
        if (foundFlight.isEmpty()) {
            return new ResponseEntity<FlightDto>(HttpStatus.NOT_FOUND);
        } else {
            final FlightDto flightDto = flightDtoMapper.flightToFlightDto(foundFlight.get());
            return new ResponseEntity<FlightDto>(flightDto, HttpStatus.FOUND);
        }
    }

    @GetMapping(ROOT_API_PATH)
    @Operation(summary = "Get flight list",
            description = "Получить список полётов")
    public ResponseEntity<List<FlightDto>> retrieveAll() {
        final List<Flight> listFound = flightService.listAll();
        final List<FlightDto> listFoundDtos = listFound.stream()
                .map(flight -> flightDtoMapper.flightToFlightDto(flight)).toList();
        final ResponseEntity<List<FlightDto>> response = new ResponseEntity<>(listFoundDtos,
                HttpStatus.OK);
        return response;
    }

    @PutMapping(ROOT_API_PATH +"/{flightId}")
    @Operation(summary = "Update/Correct flight info",
            description = "Записать обновленные данные о полёте")
    public ResponseEntity<FlightDto> update(@PathVariable String flightId,
                                            @RequestBody final FlightDto flightDto) {
        log.info("PUT " + ROOT_API_PATH + "/" + flightId + flightDto.toString());
        if (!flightId.equals(flightDto.getId())) {
            throw new AirfleetIncorrectApiRequestException("Path id and inner DTO id should not differ.");
        }
        final Flight flight = flightDtoMapper.flightDtoToFlight(flightDto);
        if (!flightService.isPresent(flight)) {
            final ResponseEntity<FlightDto> responseEntity = new ResponseEntity<> (
                    flightDtoMapper.flightToFlightDto(flightService.save(flight)), HttpStatus.CREATED);
            return responseEntity;
        }
        final ResponseEntity<FlightDto> responseEntity = new ResponseEntity<> (
                flightDtoMapper.flightToFlightDto(flightService.save(flight)), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping(ROOT_API_PATH +"/{flightId}")
    @Operation(summary = "Delete a pilot",
            description = "Удалить данные пилота")
    public ResponseEntity deleteById(@PathVariable String flightId) {
        log.info("DELETE " + ROOT_API_PATH);
        flightService.deleteById(flightId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
