package com.dnd.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfigure {

	@Bean
	public OpenAPI springOpenApi() {
		return new OpenAPI()
			.info(new Info()
				.title("Basterdz Documentation")
				.description("Basterdz 서비스의 API 명세서입니다.")
				.version("v0.0.1"));
	}

}
