package com.github.syndexmx.demoairfleet.controllers.mappers;

import com.github.syndexmx.demoairfleet.controllers.dtos.PlaneTypeDto;
import com.github.syndexmx.demoairfleet.domain.PlaneTypeFields;
import com.github.syndexmx.demoairfleet.domain.PlaneType;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PlaneTypeDtoMapper {

    public static PlaneTypeDto planetypeToPlaneTypeDto(PlaneType planeType) {
        final PlaneTypeDto planetypeDto = PlaneTypeDto.builder()
                .id(planeType.getId().toString())
                .planeTypeFieldContent(planeType.getPlaneTypeFields().toString())
                .build();
        return planetypeDto;
    }

    public static PlaneType planetypeDtoToPlaneType(PlaneTypeDto planetypeDto) {
        PlaneType planeType = PlaneType.builder()
                .id(UUID.fromString(planetypeDto.getId()))
                .planeTypeFields(PlaneTypeFields.valueOf(planetypeDto.getPlaneTypeFieldContent()))
                .build();
        return planeType;
    }

    public static PlaneType planetypeDtoNoIdToPlaneType(PlaneTypeDto planetypeDto) {
        PlaneType planeType = PlaneType.builder()
                .id(UUID.randomUUID())
                .planeTypeFields(PlaneTypeFields.valueOf(planetypeDto.getPlaneTypeFieldContent()))
                .build();
        return planeType;
    }

}
