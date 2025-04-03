package com.github.syndexmx.demoairfleet.domain;



import com.github.syndexmx.demoairfleet.domain.enums.AircraftType;
import com.github.syndexmx.demoairfleet.domain.enums.Sex;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


public class PilotTestSupplierKit {

    private static UUID id = UUID.randomUUID();

    private static Sex GENERIC_FIELD_VALUE = Sex.MALE;
    private static Sex GENERIC_STRING_MODIFIED = Sex.FEMALE;

    public static Pilot getTestPilot() {
        return Pilot.builder()
                .id(id)
                .sex(GENERIC_FIELD_VALUE)
                .firstName("John")
                .secondName("Kirk")
                .lastName("Maddison")
                .birthDate(LocalDate.parse("1973-02-06"))
                .hoursInFlight(17873)
                .certifiedAircraftTypes(List.of(AircraftType.BOEING_737_NG))
                .build();
    }

    public static Pilot getModifiedTestPilot() {
        return Pilot.builder()
                .id(id)
                .sex(GENERIC_STRING_MODIFIED)
                .firstName("Mary")
                .secondName("Jerry")
                .lastName("Smith")
                .birthDate(LocalDate.parse("1983-04-16"))
                .hoursInFlight(9873)
                .certifiedAircraftTypes(List.of(AircraftType.BOEING_737_NG))
                .build();
    }

    private static UUID NON_EXISTENT_UUID = UUID.randomUUID();
    private static Sex NON_EXISTANT_VALUE = Sex.DEFAULTVALUE;

    public static Pilot getTestNonExistentPilot( ) {
        return Pilot.builder()
                .id(NON_EXISTENT_UUID)
                .sex(NON_EXISTANT_VALUE)
                .build();
    }

}
