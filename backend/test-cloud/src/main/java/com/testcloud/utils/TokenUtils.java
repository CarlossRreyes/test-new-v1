package com.testcloud.utils;

import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.testcloud.exception.JwtTokenMalformedException;
import com.testcloud.exception.JwtTokenMissingException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Component
public class TokenUtils {
    @Value("${pharmacy.web.app.jwtSecret}")
	private String jwtSecret;

	public Claims extractAllClaims( String token ){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }



	private Key getSignInKey(){
		byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
		return Keys.hmacShaKeyFor(keyBytes);
    }

	public void validateToken (final String token) throws JwtTokenMalformedException, JwtTokenMissingException{		
		try {
			Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token);
		} catch (MalformedJwtException ex) {
			throw new JwtTokenMalformedException("El JWT Token no es válido");
		} catch (ExpiredJwtException ex){
			throw new JwtTokenMalformedException("Lo sentimos. El JWT Token ha expirado!");
		} catch (UnsupportedJwtException ex){
			// System.out.println("*****ERROR UNSO: " + ex);
			throw new JwtTokenMalformedException("El JWT Token no es compatible");
		} catch (SignatureException ex ){
			throw new JwtTokenMalformedException("La firma JWT Token no es válida ");
		} catch (IllegalArgumentException ex) {
			throw new JwtTokenMissingException("JWT claims string is empty");
		}
	}
}
