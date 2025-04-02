package com.github.syndexmx.demoairfleet.repositories.mappers;


import com.github.syndexmx.demoairfleet.domain.PlaneTypeFields;
import com.github.syndexmx.demoairfleet.domain.PlaneType;
import com.github.syndexmx.demoairfleet.repositories.entities.PlaneTypeEntity;
import org.springframework.stereotype.Component;

@Component
public class PlaneTypeEntityMapper {

    public static PlaneTypeEntity planetypeToPlaneTypeEntity(PlaneType planeType) {
        final PlaneTypeEntity planetypeEntity = PlaneTypeEntity.builder()
                .planeTypeId(planeType.getId())
                .planeTypeFieldContent(planeType.getPlaneTypeFields().toString())
                .build();
        return planetypeEntity;
    }

    public static PlaneType planetypeEntityToPlaneType(PlaneTypeEntity planetypeEntity) {
        PlaneType planeType = PlaneType.builder()
                .id(planetypeEntity.getPlaneTypeId())
                .planeTypeFields(PlaneTypeFields.valueOf(planetypeEntity.getPlaneTypeFieldContent()))
                .build();
        return planeType;
    }



}
