package com.github.syndexmx.demoairfleet.repositories.mappers;


import com.github.syndexmx.demoairfleet.domain.EngineTypeFields;
import com.github.syndexmx.demoairfleet.domain.EngineType;
import com.github.syndexmx.demoairfleet.repositories.entities.EngineTypeEntity;
import org.springframework.stereotype.Component;

@Component
public class EngineTypeEntityMapper {

    public static EngineTypeEntity enginetypeToEngineTypeEntity(EngineType engineType) {
        final EngineTypeEntity enginetypeEntity = EngineTypeEntity.builder()
                .engineTypeId(engineType.getId())
                .engineTypeFieldContent(engineType.getEngineTypeFields().toString())
                .build();
        return enginetypeEntity;
    }

    public static EngineType enginetypeEntityToEngineType(EngineTypeEntity enginetypeEntity) {
        EngineType engineType = EngineType.builder()
                .id(enginetypeEntity.getEngineTypeId())
                .engineTypeFields(EngineTypeFields.valueOf(enginetypeEntity.getEngineTypeFieldContent()))
                .build();
        return engineType;
    }



}
