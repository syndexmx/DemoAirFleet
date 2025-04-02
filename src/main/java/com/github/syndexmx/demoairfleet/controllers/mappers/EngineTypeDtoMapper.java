package com.github.syndexmx.demoairfleet.controllers.mappers;

import com.github.syndexmx.demoairfleet.controllers.dtos.EngineTypeDto;
import com.github.syndexmx.demoairfleet.domain.EngineTypeFields;
import com.github.syndexmx.demoairfleet.domain.EngineType;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EngineTypeDtoMapper {

    public static EngineTypeDto enginetypeToEngineTypeDto(EngineType engineType) {
        final EngineTypeDto enginetypeDto = EngineTypeDto.builder()
                .id(engineType.getId().toString())
                .engineTypeFieldContent(engineType.getEngineTypeFields().toString())
                .build();
        return enginetypeDto;
    }

    public static EngineType enginetypeDtoToEngineType(EngineTypeDto enginetypeDto) {
        EngineType engineType = EngineType.builder()
                .id(UUID.fromString(enginetypeDto.getId()))
                .engineTypeFields(EngineTypeFields.valueOf(enginetypeDto.getEngineTypeFieldContent()))
                .build();
        return engineType;
    }

    public static EngineType enginetypeDtoNoIdToEngineType(EngineTypeDto enginetypeDto) {
        EngineType engineType = EngineType.builder()
                .id(UUID.randomUUID())
                .engineTypeFields(EngineTypeFields.valueOf(enginetypeDto.getEngineTypeFieldContent()))
                .build();
        return engineType;
    }

}
