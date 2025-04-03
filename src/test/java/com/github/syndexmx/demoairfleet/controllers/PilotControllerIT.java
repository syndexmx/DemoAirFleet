package com.github.syndexmx.demoairfleet.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.github.syndexmx.demoairfleet.controller.mappers.PilotDtoMapper;
import com.github.syndexmx.demoairfleet.domain.Pilot;
import com.github.syndexmx.demoairfleet.domain.PilotTestSupplierKit;
import com.github.syndexmx.demoairfleet.controller.dtos.PilotDto;
import com.github.syndexmx.demoairfleet.services.PilotService;
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
public class PilotControllerIT {

    private final String ROOT_API_PATH = "/api/v0/pilots";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PilotService pilotService;

    @Autowired
    private PilotDtoMapper pilotDtoMapper;

    @Test
    public void testThatPilotIsCreated() throws Exception {
        Pilot pilot = PilotTestSupplierKit.getTestPilot();
        final PilotDto pilotDto = pilotDtoMapper.pilotToPilotDto(pilot);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String pilotJson = objectMapper.writeValueAsString(pilotDto);
        mockMvc.perform(MockMvcRequestBuilders.post(ROOT_API_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(pilotJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        List<Pilot> savedPilotList = pilotService.listAll();
        assertEquals(1, savedPilotList.size());
        Pilot savedPilot = savedPilotList.get(0);
        final UUID id = savedPilot.getId();
        pilot.setId(id);
        assertEquals(pilot, savedPilot);
    }

    @Test
    public void testThatPilotIsUpdated() throws Exception {
        final Pilot pilot = PilotTestSupplierKit.getTestPilot();
        Pilot savedPilot = pilotService.create(pilot);
        final UUID id = savedPilot.getId();
        Pilot modifiedPilot = PilotTestSupplierKit.getModifiedTestPilot();
        modifiedPilot.setId(id);
        final PilotDto modifiedPilotDto = pilotDtoMapper.pilotToPilotDto(modifiedPilot);
        final ObjectMapper modifiedObjectMapper = new ObjectMapper();
        final String modifiedPilotJson = modifiedObjectMapper.writeValueAsString(modifiedPilotDto);
        mockMvc.perform(MockMvcRequestBuilders.put(ROOT_API_PATH + "/" + id.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(modifiedPilotJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(modifiedPilotJson));
    }

    @Test
    public void testThatRetrieveReturnsNotFoundWhenAbsent() throws Exception {
        final Pilot pilot = PilotTestSupplierKit.getTestNonExistentPilot();
        final UUID id = pilot.getId();
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatRetrieveReturnsPilotWhenExists() throws Exception {
        final Pilot pilot = PilotTestSupplierKit.getTestPilot();
        final Pilot pilotSaved = pilotService.create(pilot);
        final UUID id = pilotSaved.getId();
        final PilotDto pilotDto = pilotDtoMapper.pilotToPilotDto(pilotSaved);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String pilotJson = objectMapper.writeValueAsString(pilotDto);
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().json(pilotJson));
    }

    @Test
    public void testThatRetrieveAllReturnsEmptyWhenAbsent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testThatRetrieveAllReturnsListWhenExist() throws Exception {
        final Pilot pilot = PilotTestSupplierKit.getTestPilot();
        final Pilot pilotSaved = pilotService.create(pilot);
        final PilotDto pilotDto = pilotDtoMapper.pilotToPilotDto(pilotSaved);
        final List<PilotDto> listPilotDto = new ArrayList<>(List.of(pilotDto));
        final ObjectMapper objectMapper = new ObjectMapper();
        final String pilotListJson = objectMapper.writeValueAsString(listPilotDto);
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(pilotListJson));

    }

    @Test
    public void testThatDeletePilotByIdReturnsHttp204WhenAbsent() throws Exception {
        final Pilot pilot = PilotTestSupplierKit.getTestPilot();
        final UUID id = pilot.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatDeletePilotByIdDeletesPilot() throws Exception {
        final Pilot pilot = PilotTestSupplierKit.getTestPilot();
        final Pilot savedPilot = pilotService.save(pilot);
        final UUID id = savedPilot.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete(ROOT_API_PATH + "/" + id))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
