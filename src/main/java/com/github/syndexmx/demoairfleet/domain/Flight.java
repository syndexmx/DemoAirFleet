package com.github.syndexmx.demoairfleet.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Flight {

    private UUID id;

    private FlightData flightData;

}
