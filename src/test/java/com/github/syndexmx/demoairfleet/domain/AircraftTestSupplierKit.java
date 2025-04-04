package com.github.syndexmx.demoairfleet.domain;



import com.github.syndexmx.demoairfleet.domain.enums.AircraftType;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


public class AircraftTestSupplierKit {

    private static Long id = 1L;

    private static AircraftType FIRST_AIRCRAFT_TYPE_MODIFIED = AircraftType.BOEING_737_600;
    private static AircraftType SECOND_AIRCRAFT_TYPE_MODIFIED = AircraftType.BOEING_737_700ER;

    public static Aircraft getTestAircraft() {
        return Aircraft.builder()
                .id(id)
                .aircraftType(FIRST_AIRCRAFT_TYPE_MODIFIED)
                .lastInspection(LocalDate.parse("2025-01-04"))
                .startOperation(LocalDate.parse("2010-01-01"))
                .engineCount(2)
                .engineList(List.of())
                .hoursInFlight(6009)
                .livery("Snow white with a red stripe")
                .pax(146)
                .registration("RU85833")
                .serialNumber("298743")
                .build();
    }

    public static Aircraft getModifiedTestAircraft() {
        return Aircraft.builder()
                .id(id)
                .aircraftType(SECOND_AIRCRAFT_TYPE_MODIFIED)
                .lastInspection(LocalDate.parse("2024-12-23"))
                .startOperation(LocalDate.parse("2012-01-01"))
                .engineCount(2)
                .engineList(List.of())
                .hoursInFlight(3000)
                .livery("Bright red")
                .pax(234)
                .registration("RU8665")
                .serialNumber("198743")
                .build();
    }

    private static Long NON_EXISTENT_ID = 2L;
    private static AircraftType NON_EXISTANT_VALUE = AircraftType.DEFAULTVALUE;

    public static Aircraft getTestNonExistentAircraft( ) {
        return Aircraft.builder()
                .id(NON_EXISTENT_ID)
                .aircraftType(NON_EXISTANT_VALUE)
                .build();
    }

}
