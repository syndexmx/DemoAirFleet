package com.github.syndexmx.demoairfleet.domain;



import java.time.LocalDate;
import java.util.UUID;


public class FlightTestSupplierKit {

    private static UUID id = UUID.randomUUID();

    public static Flight getTestFlight() {
        return Flight.builder()
                .id(id)
                .name("693")
                .route("MH-370")
                .pax(230)
                .callsign("8673")
                .date(LocalDate.parse("2024-11-09"))
                .build();
    }

    public static Flight getModifiedTestFlight() {
        return Flight.builder()
                .id(id)
                .name("693")
                .route("MH-370")
                .pax(230)
                .callsign("8673")
                .date(LocalDate.parse("2024-11-09"))
                .build();
    }

    private static UUID NON_EXISTENT_UUID = UUID.randomUUID();

    public static Flight getTestNonExistentFlight( ) {
        return Flight.builder()
                .id(NON_EXISTENT_UUID)
                .build();
    }

}
