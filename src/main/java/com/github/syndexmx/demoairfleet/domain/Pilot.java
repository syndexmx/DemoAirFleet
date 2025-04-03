package com.github.syndexmx.demoairfleet.domain;

import com.github.syndexmx.demoairfleet.domain.enums.AircraftType;
import com.github.syndexmx.demoairfleet.domain.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Pilot {

    private UUID id;
    private String firstName;
    private String secondName;
    private String lastName;
    private LocalDate birthDate;
    private Integer hoursInFlight;
    private Sex sex;
    private List<AircraftType> certifiedAircraftTypes;

}
