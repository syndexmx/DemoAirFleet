package com.github.syndexmx.demoairfleet.controller.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.github.syndexmx.demoairfleet.controller.mappers.FlightDtoMapper;
import com.github.syndexmx.demoairfleet.domain.Flight;
import com.github.syndexmx.demoairfleet.domain.FlightTestSupplierKit;
import com.github.syndexmx.demoairfleet.controller.dtos.FlightDto;
import com.github.syndexmx.demoairfleet.services.FlightService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class FlightControllerIT {

    private final String ROOT_API_PATH = "/api/v0/flights";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightDtoMapper flightDtoMapper;

    @Test
    public void testThatFlightIsCreated() throws Exception {
        Flight flight = FlightTestSupplierKit.getTestFlight();
        final FlightDto flightDto = flightDtoMapper.flightToFlightDto(flight);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String flightJson = objectMapper.writeValueAsString(flightDto);
        mockMvc.perform(MockMvcRequestBuilders.post(ROOT_API_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(flightJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        List<Flight> savedFlightList = flightService.listAll();
        assertEquals(1, savedFlightList.size());
        Flight savedFlight = savedFlightList.get(0);
        final UUID id = savedFlight.getId();
        flight.setId(id);
        assertEquals(flight, savedFlight);
    }

    @Test
    public void testThatFlightIsUpdated() throws Exception {
        final Flight flight = FlightTestSupplierKit.getTestFlight();
        Flight savedFlight = flightService.create(flight);
        final UUID id = savedFlight.getId();
        Flight modifiedFlight = FlightTestSupplierKit.getModifiedTestFlight();
        modifiedFlight.setId(id);
        final FlightDto modifiedFlightDto = flightDtoMapper.flightToFlightDto(modifiedFlight);
        final ObjectMapper modifiedObjectMapper = new ObjectMapper();
        final String modifiedFlightJson = modifiedObjectMapper.writeValueAsString(modifiedFlightDto);
        mockMvc.perform(MockMvcRequestBuilders.put(ROOT_API_PATH + "/" + id.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(modifiedFlightJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(modifiedFlightJson));
    }

    @Test
    public void testThatRetrieveReturnsNotFoundWhenAbsent() throws Exception {
        final Flight flight = FlightTestSupplierKit.getTestNonExistentFlight();
        final UUID id = flight.getId();
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatRetrieveReturnsFlightWhenExists() throws Exception {
        final Flight flight = FlightTestSupplierKit.getTestFlight();
        final Flight flightSaved = flightService.create(flight);
        final UUID id = flightSaved.getId();
        final FlightDto flightDto = flightDtoMapper.flightToFlightDto(flightSaved);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String flightJson = objectMapper.writeValueAsString(flightDto);
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().json(flightJson));
    }

    @Test
    public void testThatRetrieveAllReturnsEmptyWhenAbsent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testThatRetrieveAllReturnsListWhenExist() throws Exception {
        final Flight flight = FlightTestSupplierKit.getTestFlight();
        final Flight flightSaved = flightService.create(flight);
        final FlightDto flightDto = flightDtoMapper.flightToFlightDto(flightSaved);
        final List<FlightDto> listFlightDto = new ArrayList<>(List.of(flightDto));
        final ObjectMapper objectMapper = new ObjectMapper();
        final String flightListJson = objectMapper.writeValueAsString(listFlightDto);
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(flightListJson));

    }

    @Test
    public void testThatDeleteFlightByIdReturnsHttp204WhenAbsent() throws Exception {
        final Flight flight = FlightTestSupplierKit.getTestFlight();
        final UUID id = flight.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatDeleteFlightByIdDeletesFlight() throws Exception {
        final Flight flight = FlightTestSupplierKit.getTestFlight();
        final Flight savedFlight = flightService.save(flight);
        final UUID id = savedFlight.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete(ROOT_API_PATH + "/" + id))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
