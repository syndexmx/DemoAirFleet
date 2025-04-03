package com.github.syndexmx.demoairfleet.repository.entities;


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
@Table(name = "pilots")
public class PilotEntity {

    @Id
    private UUID pilotId;

    private String pilotFieldContent;


}
