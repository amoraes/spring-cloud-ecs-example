package com.github.amoraes.weatherservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerDocketAutoConfiguration {

    @Value("${application.name:No application.name property found}")
    private String applicationName;

    @Autowired(required = false)
    private BuildProperties buildProperties;

    @Bean
    public OpenAPI api() {
        return new OpenAPI().info(
                new Info().title(applicationName)
                        .version((buildProperties != null && buildProperties.getVersion() != null) ? buildProperties.getVersion() : "undefined"));
    }

}
