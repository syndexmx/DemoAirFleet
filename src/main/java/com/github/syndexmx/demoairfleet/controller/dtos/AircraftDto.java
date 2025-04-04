package com.github.syndexmx.demoairfleet.controller.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(name = "Aircraft",
        description = "Самолёт (борт)")
public class AircraftDto {

    @Schema(name = "id", description = "Идентификатор", example = "1")
    private Long id;

    @Schema(name = "Aircraft type", description = "Тип самолёта", example = "Boeing-737-600 NG")
    private String aircraftType;

    @Schema(name = "Serial Number", description = "Серийный номер", example = "48735641")
    private String serialNumber;

    @Schema(name = "Register number", description = "Регистрационный номер", example = "RU-75384")
    private String registration;

    @Schema(name = "Livery", description = "Ливрея", example = "Snow White with a red stripe")
    private String livery;

    @Schema(name = "Number of passangers", description = "Количество пассажиров", example = "220")
    private Integer pax;

    @Schema(name = "Number of engines", description = "Количество двигателей", example = "2")
    private Integer engineCount;

    @Schema(name = "Hours in flight by now", description = "Налёт", example = "10445")
    private Integer hoursInFlight;

    @Schema(name = "Start operation date", description = "Дата ввода в эксплуатацию", example = "2011-07-11")
    private String startOperation;

    @Schema(name = "Last inspection date", description = "Дата последнего осмотра", example = "2024-12-26")
    private String lastInspection;

    @Schema(name = "Engine list", description = "Список установленных двигателей", example = "[]")
    private List<EngineDto> engineList;

}
