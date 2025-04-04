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
@Schema(name = "Pilot",
        description = "Пилот")
public class PilotDto {

    @Schema(name = "id", description = "Идентификатор", example = "19")
    private String id;

    @Schema(name = "First name", description = "Имя", example = "John")
    private String firstName;

    @Schema(name = "Second name", description = "Второе имя", example = "James")
    private String secondName;

    @Schema(name = "Last name", description = "Фамилия", example = "Marshall")
    private String lastName;

    @Schema(name = "Date of birth", description = "Дата рождения", example = "1980-04-09")
    private String birthDate;

    @Schema(name = "Hours flown", description = "Налёт часов", example = "8709")
    private Integer hoursInFlight;

    @Schema(name = "Sex", description = "Пол", example = "MALE")
    private String sex;

    @Schema(name = "Certified on aircraft types (List)",
            description = "Сертифицирован для полетов на типах самолётов",
            example = "[ \"\"Boeing-737-600 NG\"\", \"\"Boeing-777-200 ER\"\" ]")
    private List<String> certifiedAircraftTypes;

}
