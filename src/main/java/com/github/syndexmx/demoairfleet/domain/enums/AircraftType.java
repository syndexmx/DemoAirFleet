package com.github.syndexmx.demoairfleet.domain.enums;

import lombok.Getter;

@Getter
public enum AircraftType {
    DEFAULTVALUE ("Not Defined"),
    BOEING_737_600 ("Boeing-737-600"),
    BOEING_737_700ER ("Boeing-737-700 ER"),
    BOEING_737_NG ("Boeing-737-600 NG"),
    BOEING_737_900 ("Boeing-737-900"),
    AIRBUS_A320_NEO ("Airbus A320 NEO"),
    BOEING_777_200ER ("Boeing-777-200 ER"),
    BOEING_777_2H6ER ("Boeing-777-2H6 ER"),
    BOEING_737_MAX_7 ("Boeing-737 MAX-7");

    private String typeName;

    AircraftType(String typeName) {
        this.typeName = typeName;
    }
}
