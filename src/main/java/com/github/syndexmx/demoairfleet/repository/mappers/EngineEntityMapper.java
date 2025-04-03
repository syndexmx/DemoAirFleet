package com.github.syndexmx.demoairfleet.repository.mappers;


import com.github.syndexmx.demoairfleet.domain.EngineType;
import com.github.syndexmx.demoairfleet.domain.Engine;
import com.github.syndexmx.demoairfleet.repository.entities.EngineEntity;
import org.springframework.stereotype.Component;


@Component
public class EngineEntityMapper {

    public EngineEntity engineToengineEntity(Engine engine) {
        final EngineEntity engineEntity = EngineEntity.builder()
                .engineId(engine.getId())
                .engineFieldContent(engine.getEngineType().toString())
                .build();
        return engineEntity;
    }

    public Engine engineEntityToengine(EngineEntity engineEntity) {
        Engine engine = Engine.builder()
                .id(engineEntity.getEngineId())
                .engineType(EngineType.valueOf(engineEntity.getEngineFieldContent()))
                .build();
        return engine;
    }



}
