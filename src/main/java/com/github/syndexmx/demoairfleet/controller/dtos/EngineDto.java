package com.github.syndexmx.demoairfleet.controller.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(name = "Engine",
        description = "Двигатель")
public class EngineDto {

    private Long id;
    private String engineTypeName;
    private String serialNumber;
    private Integer hoursInFlight;
    private String startOperation;
    private String lastInspection;

}
