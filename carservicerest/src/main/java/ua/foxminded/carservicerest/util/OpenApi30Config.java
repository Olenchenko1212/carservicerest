package ua.foxminded.carservicerest.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;




@Configuration
@OpenAPIDefinition(info = @Info(title = "My API", version = "v1"), security = @SecurityRequirement(name = "bearerAuth"))
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class OpenApi30Config {
	private final String moduleName;
	private final String apiVersion;

	public OpenApi30Config(@Value("${module-name}") String moduleName, @Value("${api-version}") String apiVersion) {
		this.moduleName = moduleName;
		this.apiVersion = apiVersion;
	}

	@Bean
	public OpenAPI customOpenAPI() {
		final String securitySchemeName = "bearerAuth";
		final String apiTitle = String.format("%s API", StringUtils.capitalize(moduleName));
		return new OpenAPI()
				.addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList(securitySchemeName))
				.components(
						new Components().addSecuritySchemes(
								securitySchemeName,
								new io.swagger.v3.oas.models.security.SecurityScheme()
										.name(securitySchemeName)
										.type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
										.scheme("bearer")
										.bearerFormat("JWT")))
				.info(new io.swagger.v3.oas.models.info.Info().title(apiTitle).version(apiVersion));
	}
}
