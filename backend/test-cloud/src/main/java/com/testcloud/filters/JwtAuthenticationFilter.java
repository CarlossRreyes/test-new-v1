package com.testcloud.filters;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testcloud.exception.JwtTokenMalformedException;
import com.testcloud.exception.JwtTokenMissingException;
import com.testcloud.utils.TokenUtils;

import reactor.core.publisher.Mono;



@Component
public class JwtAuthenticationFilter implements GatewayFilter {

 @Autowired
    private TokenUtils tokenUtils;

	@Autowired
	private RouteValidator routeValidator;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		if( routeValidator.isSecured.test( exchange.getRequest() )){
			if( !exchange.getRequest().getHeaders().containsKey( HttpHeaders.AUTHORIZATION ) ){
				ServerHttpResponse response = exchange.getResponse();
				response.setStatusCode( HttpStatus.UNAUTHORIZED );
				response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

				// return response.setComplete();
				return response.writeWith(Mono.just( responseFailedJwt(response, "No existe JWT Token" )));
				// return response.setComplete();
			}

			String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
			if( authHeader != null && authHeader.startsWith("Bearer ")){
				authHeader = authHeader.substring(7);
			}

			try {
				tokenUtils.validateToken(authHeader);
			} catch (JwtTokenMalformedException | JwtTokenMissingException e) {
				ServerHttpResponse response = exchange.getResponse();
				response.setStatusCode( HttpStatus.NOT_FOUND );
				// response.setStatusCode( HttpStatus.INTERNAL_SERVER_ERROR );
				response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
				
				return response.writeWith(Mono.just( responseFailedJwt(response, e.getMessage())));
			}
		}

		return chain.filter(exchange);
	}


	private DataBuffer responseFailedJwt ( ServerHttpResponse response, String message ){

		ObjectMapper objectMapper = new ObjectMapper();		
		HashMap<String, Object> mapResponse = new HashMap<>();
		mapResponse.put("message", message);
		mapResponse.put("status", false);
		try {
			byte[] dataBytes = objectMapper.writeValueAsBytes(mapResponse);
			DataBuffer dataBuffer = response.bufferFactory().wrap(dataBytes);
			return dataBuffer;
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		}
		return null;
	}
}
