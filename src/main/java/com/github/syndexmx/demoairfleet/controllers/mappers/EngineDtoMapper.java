package com.github.syndexmx.demoairfleet.controllers.mappers;

import com.github.syndexmx.demoairfleet.controllers.dtos.EngineDto;
import com.github.syndexmx.demoairfleet.domain.EngineFields;
import com.github.syndexmx.demoairfleet.domain.Engine;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EngineDtoMapper {

    public static EngineDto engineToEngineDto(Engine engine) {
        final EngineDto engineDto = EngineDto.builder()
                .id(engine.getId().toString())
                .engineFieldContent(engine.getEngineFields().toString())
                .build();
        return engineDto;
    }

    public static Engine engineDtoToEngine(EngineDto engineDto) {
        Engine engine = Engine.builder()
                .id(UUID.fromString(engineDto.getId()))
                .engineFields(EngineFields.valueOf(engineDto.getEngineFieldContent()))
                .build();
        return engine;
    }

    public static Engine engineDtoNoIdToEngine(EngineDto engineDto) {
        Engine engine = Engine.builder()
                .id(UUID.randomUUID())
                .engineFields(EngineFields.valueOf(engineDto.getEngineFieldContent()))
                .build();
        return engine;
    }

}
