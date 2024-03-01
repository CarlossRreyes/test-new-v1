package com.testauth.security.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.testauth.entities.User;
import com.testauth.repositories.IUserRepository;
import com.testauth.security.entities.AuthCredentials;
import com.testauth.utils.RequestResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServices {

     private final AuthenticationManager authenticationManager;
    private final JwtServices jwtServices;
    private final IUserRepository userRepository;

    public RequestResponse authenticate( AuthCredentials authCredentials ){        
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authCredentials.getEmail(), authCredentials.getPassword())
            );                                
            User userDetails = userRepository.findOneByEmail( authCredentials.getEmail() ).orElseThrow();   

            // **************GENERATE TOKEN***********
            String jwt = jwtServices.generateToken( addClaims(userDetails), userDetails );

            return new RequestResponse( true, "Acceso al sistema.", jwt );                
        } catch (Exception e) {            
            return new RequestResponse( false, "Las credenciales no coinciden.", null );
        }
    }


    
    private Map<String, Object> addClaims ( User userDetails ){
        Map<String, Object> requestUser = new HashMap<>();
        requestUser.put("user", userDetails);
        // requestUser.put("permissionList", listPermission);
        return requestUser;
    }
    
}
