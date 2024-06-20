package ua.foxminded.carservicerest.util;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class AuthOpenApiCustomiser implements OpenApiCustomizer {
	 @Override
	    public void customise(OpenAPI openApi) {
	        var securitySchemeName = "bearerAuth";
	        openApi.getComponents().addSecuritySchemes(securitySchemeName, new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"));
	        openApi.addSecurityItem(new SecurityRequirement().addList(securitySchemeName));
	    }
}
