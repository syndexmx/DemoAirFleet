package com.github.syndexmx.demoairfleet.repository.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    private String firstName;
    private String secondName;
    private String lastName;
    private LocalDate birthDate;
    private Integer hoursInFlight;
    private String sex;


}
