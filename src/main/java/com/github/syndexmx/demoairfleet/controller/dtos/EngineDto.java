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

    @Schema(name = "id", description = "Идентификатор", example = "1")
    private Long id;

    @Schema(name = "Engine type", description = "Тип двигателя", example = "Pratt&Whitney JT8D-1")
    private String engineTypeName;

    @Schema(name = "Serial number", description = "Серийный номер", example = "39947521")
    private String serialNumber;

    @Schema(name = "Hours flown", description = "Налёт часов", example = "11960")
    private Integer hoursInFlight;

    @Schema(name = "Start operation date", description = "Дата ввода в эксплуатацию", example = "2011-07-11")
    private String startOperation;

    @Schema(name = "Last inspection date", description = "Дата последнего осмотра", example = "2024-12-26")
    private String lastInspection;

}
