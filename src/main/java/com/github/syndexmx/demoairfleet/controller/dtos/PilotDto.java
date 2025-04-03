package com.github.syndexmx.demoairfleet.controller.dtos;


import com.github.syndexmx.demoairfleet.domain.Aircraft;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PilotDto {

    private String id;
    private String firstName;
    private String secondName;
    private String lastName;
    private String birthDate;
    private Integer hoursInFlight;
    private String sex;
    private List<String> certifiedAircraftTypes;

}
