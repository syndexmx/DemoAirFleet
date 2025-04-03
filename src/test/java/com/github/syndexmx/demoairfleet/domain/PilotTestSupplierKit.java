package com.github.syndexmx.demoairfleet.domain;



import java.util.UUID;


public class PilotTestSupplierKit {

    private static UUID id = UUID.randomUUID();

    private static PilotField GENERIC_FIELD_VALUE = PilotField.DEFAULTVALUE;
    private static PilotField GENERIC_STRING_MODIFIED = PilotField.ALTERNATIVEVALUE;

    public static Pilot getTestPilot() {
        return Pilot.builder()
                .id(id)
                .pilotField(GENERIC_FIELD_VALUE)
                .build();
    }

    public static Pilot getModifiedTestPilot() {
        return Pilot.builder()
                .id(id)
                .pilotField(GENERIC_STRING_MODIFIED)
                .build();
    }

    private static UUID NON_EXISTENT_UUID = UUID.randomUUID();
    private static PilotField NON_EXISTANT_VALUE = PilotField.OTHERVALUE;

    public static Pilot getTestNonExistentPilot( ) {
        return Pilot.builder()
                .id(NON_EXISTENT_UUID)
                .pilotField(NON_EXISTANT_VALUE)
                .build();
    }

}
