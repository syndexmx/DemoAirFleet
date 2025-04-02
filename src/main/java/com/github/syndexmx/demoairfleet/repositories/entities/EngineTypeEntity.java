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
@Table(name = "enginetypes")
public class EngineTypeEntity {

    @Id
    private UUID engineTypeId;

    private String engineTypeFieldContent;


}
