package com.github.syndexmx.demoairfleet.domain;



import java.time.LocalDate;
import java.util.UUID;


public class EngineTestSupplierKit {

    private static UUID id = UUID.randomUUID();

    private static EngineType GENERIC_FIELD_VALUE = EngineType.DEFAULTVALUE;
    private static EngineType GENERIC_STRING_MODIFIED = EngineType.ROLLSROYCE_TRENT_877;

    public static Engine getTestengine() {
        return Engine.builder()
                .id(id)
                .engineType(GENERIC_FIELD_VALUE)
                .lastInspection(LocalDate.parse("2025-01-01"))
                .startOperation(LocalDate.parse("2020-02-02"))
                .hoursInFlight(3999)
                .serialNumber("58830443")
                .build();
    }

    public static Engine getModifiedTestengine() {
        return Engine.builder()
                .id(id)
                .engineType(GENERIC_STRING_MODIFIED)
                .lastInspection(LocalDate.parse("2024-11-21"))
                .startOperation(LocalDate.parse("2019-02-02"))
                .hoursInFlight(1788)
                .serialNumber("8777583")
                .build();
    }

    private static UUID NON_EXISTENT_UUID = UUID.randomUUID();
    private static EngineType NON_EXISTANT_VALUE = EngineType.CFM56_7;

    public static Engine getTestNonExistentengine( ) {
        return Engine.builder()
                .id(NON_EXISTENT_UUID)
                .engineType(NON_EXISTANT_VALUE)
                .build();
    }

}
