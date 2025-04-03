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
@Table(name = "engines")
public class EngineEntity {

    @Id
    private UUID engineId;

    private String engineFieldContent;


}
