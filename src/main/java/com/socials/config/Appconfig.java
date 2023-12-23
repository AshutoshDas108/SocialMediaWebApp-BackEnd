package com.socials.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class Appconfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http.sessionManagement(management -> management.sessionCreationPolicy(
				SessionCreationPolicy.STATELESS))
		        .authorizeHttpRequests(Authorize -> Authorize
				.requestMatchers("/api/**").authenticated()
				.anyRequest().permitAll())
		        .addFilterBefore(new jwtValidator(), BasicAuthenticationFilter.class)
		        .csrf(csrf -> csrf.disable())
				.cors(cors->cors.configurationSource(corsConfigurationSource()));

		
		return http.build();
	}
	
	
	@Bean
	 PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }

	 //CORS configuration added
	 private CorsConfigurationSource corsConfigurationSource(){
		return  new CorsConfigurationSource() {
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration cfg = new CorsConfiguration();
				//specify the urls of our front end
				cfg.setAllowedOrigins(Arrays.asList(
						"http://localhost:3000",
						"http://localhost:4000",
						"http://localhost:4200"
						//"https://deployed-app link"
				));
				//All type of methods are allowed
				cfg.setAllowedMethods(Collections.singletonList("*"));
				cfg.setAllowCredentials(true);
				//All headers are enabled
				cfg.setAllowedHeaders(Collections.singletonList("*"));
				//can be added if needed
				cfg.setExposedHeaders(Arrays.asList(
						"Authorization"
				));
				cfg.setMaxAge(3600L);

				return cfg;
			}
		};
	 }

}
