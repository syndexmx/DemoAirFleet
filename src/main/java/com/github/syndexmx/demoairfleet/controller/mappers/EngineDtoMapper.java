package com.github.syndexmx.demoairfleet.controller.mappers;


import com.github.syndexmx.demoairfleet.controller.dtos.EngineDto;
import com.github.syndexmx.demoairfleet.domain.EngineType;
import com.github.syndexmx.demoairfleet.domain.Engine;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class EngineDtoMapper {

    public EngineDto engineToengineDto(Engine engine) {
        final EngineDto engineDto = EngineDto.builder()
                .id(engine.getId().toString())
                .engineFieldContent(engine.getEngineType().toString())
                .build();
        return engineDto;
    }

    public Engine engineDtoToengine(EngineDto engineDto) {
        Engine engine = Engine.builder()
                .id(UUID.fromString(engineDto.getId()))
                .engineType(EngineType.valueOf(engineDto.getEngineFieldContent()))
                .build();
        return engine;
    }

    public Engine engineDtoNoIdToengine(EngineDto engineDto) {
        Engine engine = Engine.builder()
                .id(UUID.randomUUID())
                .engineType(EngineType.valueOf(engineDto.getEngineFieldContent()))
                .build();
        return engine;
    }

}
