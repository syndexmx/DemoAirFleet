package com.github.syndexmx.demoairfleet.controller.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EngineDto {

    private String id;

    private String engineFieldContent;

}
