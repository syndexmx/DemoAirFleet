package com.github.syndexmx.demoairfleet.repositories.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "aircrafts")
public class AircraftEntity {

    @Id
    private UUID aircraftId;

    private String aircraftFieldContent;


}
