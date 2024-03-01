package com.testauth.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.testauth.security.filters.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebSecuriryConfig {

    
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    SecurityFilterChain securityFilterChain( HttpSecurity http ) throws Exception{
        http
            .csrf( crsf -> crsf.disable())
            // .authorizeHttpRequests( (authorize) -> authorize.requestMatchers("/api/auth/test").permitAll() )
            .authorizeHttpRequests((authorize) -> authorize
                // .requestMatchers("/api/auth/login").permitAll()
                .requestMatchers("/api/auth/login").permitAll()

                .anyRequest()
                .authenticated())
            
            .sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) )
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            ;        
        return http.build();
    }

    // public static void main(String [] args) {

	// 	System.out.println("********PASSWORD: " + new BCryptPasswordEncoder().encode("Carloss123*"));



	    
	// }
    
}
