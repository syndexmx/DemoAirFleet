package com.github.syndexmx.demoairfleet.controller.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Demo AirFleet API",
                description = "API демо-системы информации о авиационном флоте компании",
                version = "0.0.0",
                contact = @Contact(
                        name = "Shapovalov Maxim",
                        url = "https://github.com/syndexmx"
                )
        )
)
public class OpenApiCofig {
}
