package com.jarmison.incassdev.core.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "API", version = "1.0", description = "API REST"))
public class SwaggerConfig {
	@Bean
	public GroupedOpenApi apiPublic() {
		return GroupedOpenApi.builder().group("public-api").packagesToScan("com.jarmison.incassdev.application.v1")
				.build();
	}
}