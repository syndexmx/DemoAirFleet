package com.github.syndexmx.demoairfleet.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.github.syndexmx.demoairfleet.controller.mappers.EngineDtoMapper;
import com.github.syndexmx.demoairfleet.domain.Engine;
import com.github.syndexmx.demoairfleet.domain.EngineTestSupplierKit;
import com.github.syndexmx.demoairfleet.controller.dtos.EngineDto;
import com.github.syndexmx.demoairfleet.services.EngineService;
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
public class EngineControllerIT {

    private final String ROOT_API_PATH = "/api/v0/engines";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EngineService engineService;

    @Autowired
    private EngineDtoMapper engineDtoMapper;

    @Test
    public void testThatengineIsCreated() throws Exception {
        Engine engine = EngineTestSupplierKit.getTestengine();
        final EngineDto engineDto = engineDtoMapper.engineToengineDto(engine);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String engineJson = objectMapper.writeValueAsString(engineDto);
        mockMvc.perform(MockMvcRequestBuilders.post(ROOT_API_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(engineJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        List<Engine> savedengineList = engineService.listAll();
        assertEquals(1, savedengineList.size());
        Engine savedengine = savedengineList.get(0);
        final UUID id = savedengine.getId();
        engine.setId(id);
        assertEquals(engine, savedengine);
    }

    @Test
    public void testThatengineIsUpdated() throws Exception {
        final Engine engine = EngineTestSupplierKit.getTestengine();
        Engine savedengine = engineService.create(engine);
        final UUID id = savedengine.getId();
        Engine modifiedEngine = EngineTestSupplierKit.getModifiedTestengine();
        modifiedEngine.setId(id);
        final EngineDto modifiedengineDto = engineDtoMapper.engineToengineDto(modifiedEngine);
        final ObjectMapper modifiedObjectMapper = new ObjectMapper();
        final String modifiedengineJson = modifiedObjectMapper.writeValueAsString(modifiedengineDto);
        mockMvc.perform(MockMvcRequestBuilders.put(ROOT_API_PATH + "/" + id.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(modifiedengineJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(modifiedengineJson));
    }

    @Test
    public void testThatRetrieveReturnsNotFoundWhenAbsent() throws Exception {
        final Engine engine = EngineTestSupplierKit.getTestNonExistentengine();
        final UUID id = engine.getId();
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatRetrieveReturnsengineWhenExists() throws Exception {
        final Engine engine = EngineTestSupplierKit.getTestengine();
        final Engine engineSaved = engineService.create(engine);
        final UUID id = engineSaved.getId();
        final EngineDto engineDto = engineDtoMapper.engineToengineDto(engineSaved);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String engineJson = objectMapper.writeValueAsString(engineDto);
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().json(engineJson));
    }

    @Test
    public void testThatRetrieveAllReturnsEmptyWhenAbsent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testThatRetrieveAllReturnsListWhenExist() throws Exception {
        final Engine engine = EngineTestSupplierKit.getTestengine();
        final Engine engineSaved = engineService.create(engine);
        final EngineDto engineDto = engineDtoMapper.engineToengineDto(engineSaved);
        final List<EngineDto> listengineDto = new ArrayList<>(List.of(engineDto));
        final ObjectMapper objectMapper = new ObjectMapper();
        final String engineListJson = objectMapper.writeValueAsString(listengineDto);
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(engineListJson));

    }

    @Test
    public void testThatDeleteengineByIdReturnsHttp204WhenAbsent() throws Exception {
        final Engine engine = EngineTestSupplierKit.getTestengine();
        final UUID id = engine.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatDeleteengineByIdDeletesengine() throws Exception {
        final Engine engine = EngineTestSupplierKit.getTestengine();
        final Engine savedEngine = engineService.save(engine);
        final UUID id = savedEngine.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete(ROOT_API_PATH + "/" + id))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
