package com.github.syndexmx.demoairfleet.controller.mappers;

import com.github.syndexmx.demoairfleet.controller.dtos.EngineDto;
import com.github.syndexmx.demoairfleet.domain.enums.EngineType;
import com.github.syndexmx.demoairfleet.domain.Engine;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
public class EngineDtoMapper {

    private static Map<String, EngineType> typeNameToTypeType = new HashMap<>();

    public EngineDtoMapper() {
        for (EngineType engineType : EngineType.values()) {
            typeNameToTypeType.put(engineType.getTypeName(), engineType);
        }
    }

    public static EngineDto engineToEngineDto(Engine engine) {
        final EngineDto engineDto = EngineDto.builder()
                .id(engine.getId())
                .engineTypeName(engine.getEngineType().getTypeName())
                .lastInspection(engine.getLastInspection().toString())
                .startOperation(engine.getStartOperation().toString())
                .hoursInFlight(engine.getHoursInFlight())
                .serialNumber(engine.getSerialNumber())
                .build();
        return engineDto;
    }

    public static Engine engineDtoToEngine(EngineDto engineDto) {
        Engine engine = Engine.builder()
                .id(engineDto.getId())
                .engineType(typeNameToTypeType.get(engineDto.getEngineTypeName()))
                .lastInspection(LocalDate.parse(engineDto.getLastInspection()))
                .startOperation(LocalDate.parse(engineDto.getStartOperation()))
                .hoursInFlight(engineDto.getHoursInFlight())
                .serialNumber(engineDto.getSerialNumber())
                .build();
        return engine;
    }

    public Engine engineDtoNoIdToengine(EngineDto engineDto) {
        Engine engine = Engine.builder()
                .id(null)
                .engineType(typeNameToTypeType.get(engineDto.getEngineTypeName()))
                .lastInspection(LocalDate.parse(engineDto.getLastInspection()))
                .startOperation(LocalDate.parse(engineDto.getStartOperation()))
                .hoursInFlight(engineDto.getHoursInFlight())
                .serialNumber(engineDto.getSerialNumber())
                .build();
        return engine;
    }

}
