package com.github.syndexmx.demoairfleet.controller.dtos;

import com.github.syndexmx.demoairfleet.domain.Aircraft;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(name = "Flight",
        description = "Полёт")
public class FlightDto {

    @Schema(name = "id", description = "Идентификатор", example = "17")
    private String id;

    @Schema(name = "Name of flight", description = "Наименование маршрута", example = "Madrid to New-York")
    private String name;

    @Schema(name = "Route identifier", description = "Идентификатор маршрута", example = "MH-48")
    private String route;

    @Schema(name = "Call-sign", description = "Присвоенный позывной", example = "7486")
    private String callsign;

    @Schema(name = "Number of passengers", description = "Количество пассажирова", example = "189")
    private Integer pax;

    @Schema(name = "Date", description = "Дата полёта", example = "2025-03-19")
    private String date;

    @Schema(name = "Aircraft", description = "Самолёт (борт)", example = "")
    private AircraftDto aircraft;

    @Schema(name = "Captain", description = "Капитан", example = "[]")
    private PilotDto captain;

    @Schema(name = "First officer", description = "Второй пилот", example = "[]")
    private PilotDto firstOfficer;

}
