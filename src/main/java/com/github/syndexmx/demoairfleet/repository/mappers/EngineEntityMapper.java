package com.github.syndexmx.demoairfleet.repository.mappers;


import com.github.syndexmx.demoairfleet.domain.enums.EngineType;
import com.github.syndexmx.demoairfleet.domain.Engine;
import com.github.syndexmx.demoairfleet.repository.entities.EngineEntity;
import org.springframework.stereotype.Component;


@Component
public class EngineEntityMapper {

    public EngineEntity engineToEngineEntity(Engine engine) {
        final EngineEntity engineEntity = EngineEntity.builder()
                .id(engine.getId())
                .engineType(engine.getEngineType().toString())
                .lastInspection(engine.getLastInspection())
                .startOperation(engine.getStartOperation())
                .hoursInFlight(engine.getHoursInFlight())
                .serialNumber(engine.getSerialNumber())
                .build();
        return engineEntity;
    }

    public Engine engineEntityToEngine(EngineEntity engineEntity) {
        Engine engine = Engine.builder()
                .id(engineEntity.getId())
                .engineType(EngineType.valueOf(engineEntity.getEngineType()))
                .lastInspection(engineEntity.getLastInspection())
                .startOperation(engineEntity.getStartOperation())
                .hoursInFlight(engineEntity.getHoursInFlight())
                .serialNumber(engineEntity.getSerialNumber())
                .build();
        return engine;
    }



}
