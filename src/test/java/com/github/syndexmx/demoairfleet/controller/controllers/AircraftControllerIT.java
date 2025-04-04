package com.github.syndexmx.demoairfleet.controller.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.github.syndexmx.demoairfleet.controller.mappers.AircraftDtoMapper;
import com.github.syndexmx.demoairfleet.domain.Aircraft;
import com.github.syndexmx.demoairfleet.domain.AircraftTestSupplierKit;
import com.github.syndexmx.demoairfleet.controller.dtos.AircraftDto;
import com.github.syndexmx.demoairfleet.services.AircraftService;
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
public class AircraftControllerIT {

    private final String ROOT_API_PATH = "/api/v0/aircrafts";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AircraftService aircraftService;

    @Autowired
    private AircraftDtoMapper aircraftDtoMapper;

    @Test
    public void testThatAircraftIsCreated() throws Exception {
        Aircraft aircraft = AircraftTestSupplierKit.getTestAircraft();
        final AircraftDto aircraftDto = aircraftDtoMapper.aircraftToAircraftDto(aircraft);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String aircraftJson = objectMapper.writeValueAsString(aircraftDto);
        mockMvc.perform(MockMvcRequestBuilders.post(ROOT_API_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(aircraftJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        List<Aircraft> savedAircraftList = aircraftService.listAll();
        assertEquals(1, savedAircraftList.size());
        Aircraft savedAircraft = savedAircraftList.get(0);
        final Long id = savedAircraft.getId();
        aircraft.setId(id);
        assertEquals(aircraft, savedAircraft);
    }

    @Test
    public void testThatAircraftIsUpdated() throws Exception {
        final Aircraft aircraft = AircraftTestSupplierKit.getTestAircraft();
        Aircraft savedAircraft = aircraftService.create(aircraft);
        final Long id = savedAircraft.getId();
        Aircraft modifiedAircraft = AircraftTestSupplierKit.getModifiedTestAircraft();
        modifiedAircraft.setId(id);
        final AircraftDto modifiedAircraftDto = aircraftDtoMapper.aircraftToAircraftDto(modifiedAircraft);
        final ObjectMapper modifiedObjectMapper = new ObjectMapper();
        final String modifiedAircraftJson = modifiedObjectMapper.writeValueAsString(modifiedAircraftDto);
        mockMvc.perform(MockMvcRequestBuilders.put(ROOT_API_PATH + "/" + id.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(modifiedAircraftJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(modifiedAircraftJson));
    }

    @Test
    public void testThatRetrieveReturnsNotFoundWhenAbsent() throws Exception {
        final Aircraft aircraft = AircraftTestSupplierKit.getTestNonExistentAircraft();
        final Long id = aircraft.getId();
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatRetrieveReturnsAircraftWhenExists() throws Exception {
        final Aircraft aircraft = AircraftTestSupplierKit.getTestAircraft();
        final Aircraft aircraftSaved = aircraftService.create(aircraft);
        final Long id = aircraftSaved.getId();
        final AircraftDto aircraftDto = aircraftDtoMapper.aircraftToAircraftDto(aircraftSaved);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String aircraftJson = objectMapper.writeValueAsString(aircraftDto);
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().json(aircraftJson));
    }

    @Test
    public void testThatRetrieveAllReturnsEmptyWhenAbsent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testThatRetrieveAllReturnsListWhenExist() throws Exception {
        final Aircraft aircraft = AircraftTestSupplierKit.getTestAircraft();
        final Aircraft aircraftSaved = aircraftService.create(aircraft);
        final AircraftDto aircraftDto = aircraftDtoMapper.aircraftToAircraftDto(aircraftSaved);
        final List<AircraftDto> listAircraftDto = new ArrayList<>(List.of(aircraftDto));
        final ObjectMapper objectMapper = new ObjectMapper();
        final String aircraftListJson = objectMapper.writeValueAsString(listAircraftDto);
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(aircraftListJson));

    }

    @Test
    public void testThatDeleteAircraftByIdReturnsHttp204WhenAbsent() throws Exception {
        final Aircraft aircraft = AircraftTestSupplierKit.getTestAircraft();
        final Long id = aircraft.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatDeleteAircraftByIdDeletesAircraft() throws Exception {
        final Aircraft aircraft = AircraftTestSupplierKit.getTestAircraft();
        final Aircraft savedAircraft = aircraftService.save(aircraft);
        final Long id = savedAircraft.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete(ROOT_API_PATH + "/" + id))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
