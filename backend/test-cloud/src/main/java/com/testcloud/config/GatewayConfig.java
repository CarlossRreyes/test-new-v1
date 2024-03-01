package com.testcloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.testcloud.filters.JwtAuthenticationFilter;



@Configuration
public class GatewayConfig {


    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		
		System.out.println("router");
		return builder.routes()
				.route("auth", r -> r.path("/api/auth/**").uri("http://localhost:4043/"))
				// .route("pharmacy-administracion-service", r -> r.path("/api/pharmacy-administration/**").filters(f->f.filter(jwtAuthenticationFilter)).uri("http://localhost:4044/"))
				.route("security-service", r -> r.path("/api/security/**").filters(f->f.filter(jwtAuthenticationFilter)).uri("http://localhost:4045/"))
				.build();
	}
    
}

