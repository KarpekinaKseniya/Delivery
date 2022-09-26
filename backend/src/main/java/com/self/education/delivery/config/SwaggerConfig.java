package com.self.education.delivery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(final Info info) {
        return new OpenAPI().info(info);
    }

    @Bean
    public Info info() {
        //@formatter:off
        return new Info()
                .title("Delivery Service API")
                .version("v1")
                .description("Contain solution on testing task");
        //@formatter:on
    }
}
