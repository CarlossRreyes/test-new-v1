package com.testsecurity.security.services;
import java.util.function.Function;
import java.security.Key;
import org.springframework.beans.factory.annotation.Value;


// import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;



import io.jsonwebtoken.Claims;

import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtServices {

    @Value("${pharmacy.web.app.jwtSecret}")
	private String jwtSecret;
    

    public <T> T extractClaims( String token, Function<Claims, T> claimsResolver ){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims( String token ){
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



    public String extractUsername( String token ){
        return extractClaims(token, Claims::getSubject);
    }

    
}
