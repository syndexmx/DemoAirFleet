package com.github.syndexmx.demoairfleet.controllers.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PlaneTypeDto {

    private String id;

    private String planeTypeFieldContent;


}
