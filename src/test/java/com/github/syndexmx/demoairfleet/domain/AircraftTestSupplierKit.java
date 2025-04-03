package com.github.syndexmx.demoairfleet.domain;



import java.util.UUID;


public class AircraftTestSupplierKit {

    private static UUID id = UUID.randomUUID();

    private static AircraftType GENERIC_FIELD_VALUE = AircraftType.DEFAULTVALUE;
    private static AircraftType GENERIC_STRING_MODIFIED = AircraftType.ALTERNATIVEVALUE;

    public static Aircraft getTestAircraft() {
        return Aircraft.builder()
                .id(id)
                .aircraftType(GENERIC_FIELD_VALUE)
                .build();
    }

    public static Aircraft getModifiedTestAircraft() {
        return Aircraft.builder()
                .id(id)
                .aircraftType(GENERIC_STRING_MODIFIED)
                .build();
    }

    private static UUID NON_EXISTENT_UUID = UUID.randomUUID();
    private static AircraftType NON_EXISTANT_VALUE = AircraftType.OTHERVALUE;

    public static Aircraft getTestNonExistentAircraft( ) {
        return Aircraft.builder()
                .id(NON_EXISTENT_UUID)
                .aircraftType(NON_EXISTANT_VALUE)
                .build();
    }

}
