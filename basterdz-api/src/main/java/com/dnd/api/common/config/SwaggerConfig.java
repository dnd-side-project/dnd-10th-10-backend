package com.dnd.api.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

	private static final String AUTHORIZATION = "Authorization";

	@Bean
	public OpenAPI springOpenApi() {
		SecurityScheme bearerAuth = new SecurityScheme()
			.type(SecurityScheme.Type.APIKEY)
			.name(AUTHORIZATION)
			.in(SecurityScheme.In.HEADER);

		SecurityRequirement securityItem = new SecurityRequirement().addList(AUTHORIZATION);

		return new OpenAPI()
			.components(new Components().addSecuritySchemes(AUTHORIZATION, bearerAuth))
			.addSecurityItem(securityItem)
			.info(new Info()
				.title("존경하는 아요분들~ \uD83C\uDF4E 늦어서 죄송합니다. 설날 때쯤이면 거의 다 작성되있지 않을까 싶어요\uD83D\uDC98")
				.description("Basterdz 서비스의 API 명세서입니다.")
				.version("v0.0.1"));
	}

}
