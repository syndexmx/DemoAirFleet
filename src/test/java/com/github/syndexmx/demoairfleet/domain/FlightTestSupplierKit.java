package com.github.syndexmx.demoairfleet.domain;



import com.github.syndexmx.demoairfleet.domain.enums.FlightData;

import java.util.UUID;


public class FlightTestSupplierKit {

    private static UUID id = UUID.randomUUID();

    private static FlightData GENERIC_FIELD_VALUE = FlightData.DEFAULTVALUE;
    private static FlightData GENERIC_STRING_MODIFIED = FlightData.ALTERNATIVEVALUE;

    public static Flight getTestFlight() {
        return Flight.builder()
                .id(id)
                .flightData(GENERIC_FIELD_VALUE)
                .build();
    }

    public static Flight getModifiedTestFlight() {
        return Flight.builder()
                .id(id)
                .flightData(GENERIC_STRING_MODIFIED)
                .build();
    }

    private static UUID NON_EXISTENT_UUID = UUID.randomUUID();
    private static FlightData NON_EXISTANT_VALUE = FlightData.OTHERVALUE;

    public static Flight getTestNonExistentFlight( ) {
        return Flight.builder()
                .id(NON_EXISTENT_UUID)
                .flightData(NON_EXISTANT_VALUE)
                .build();
    }

}
