package com.github.syndexmx.demoairfleet.domain;



import java.util.UUID;


public class EngineTestSupplierKit {

    private static UUID id = UUID.randomUUID();

    private static EngineType GENERIC_FIELD_VALUE = EngineType.DEFAULTVALUE;
    private static EngineType GENERIC_STRING_MODIFIED = EngineType.ROLLSROYCE_TRENT_877;

    public static Engine getTestengine() {
        return Engine.builder()
                .id(id)
                .engineType(GENERIC_FIELD_VALUE)
                .build();
    }

    public static Engine getModifiedTestengine() {
        return Engine.builder()
                .id(id)
                .engineType(GENERIC_STRING_MODIFIED)
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
