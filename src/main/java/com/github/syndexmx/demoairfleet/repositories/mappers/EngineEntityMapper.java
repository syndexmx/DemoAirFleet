package com.github.syndexmx.demoairfleet.repositories.mappers;


import com.github.syndexmx.demoairfleet.domain.EngineFields;
import com.github.syndexmx.demoairfleet.domain.Engine;
import com.github.syndexmx.demoairfleet.repositories.entities.EngineEntity;
import org.springframework.stereotype.Component;

@Component
public class EngineEntityMapper {

    public static EngineEntity engineToEngineEntity(Engine engine) {
        final EngineEntity engineEntity = EngineEntity.builder()
                .engineId(engine.getId())
                .engineFieldContent(engine.getEngineFields().toString())
                .build();
        return engineEntity;
    }

    public static Engine engineEntityToEngine(EngineEntity engineEntity) {
        Engine engine = Engine.builder()
                .id(engineEntity.getEngineId())
                .engineFields(EngineFields.valueOf(engineEntity.getEngineFieldContent()))
                .build();
        return engine;
    }



}
