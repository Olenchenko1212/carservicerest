package ua.foxminded.carservicerest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	private final String URI_RESOURCE = "/api/v1/cars/**";
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		return http
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(HttpMethod.POST, URI_RESOURCE)
						.hasAuthority("SCOPE_create:car")
						.requestMatchers(HttpMethod.PATCH, URI_RESOURCE)
						.hasAuthority("SCOPE_update:car")
						.requestMatchers(HttpMethod.DELETE, URI_RESOURCE)
						.hasAuthority("SCOPE_delete:car")
						.requestMatchers(HttpMethod.GET, URI_RESOURCE).authenticated()
						)
				.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(Customizer.withDefaults()))
				.build();
	}
}
