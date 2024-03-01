package com.testauth.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testauth.entities.User;
import com.testauth.security.entities.AuthCredentials;
import com.testauth.security.services.AuthServices;
import com.testauth.security.services.JwtServices;
import com.testauth.utils.RequestResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
      private final AuthServices authServices;
    private final JwtServices jwtServices;
    private final UserDetailsService userDetailsService;

    @GetMapping("/hola")
    public String  initApp(){
        return "Hola Mundo, que Dios nos ayude JEJE";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthCredentials authCredentials){  
        
        System.out.println("Credentials: " + authCredentials.getEmail());
        RequestResponse authResponse = authServices.authenticate(authCredentials);
        if( !(authResponse.isStatus()) ){
            return new ResponseEntity<RequestResponse>( authResponse, HttpStatus.OK);
        }
        return new ResponseEntity<RequestResponse>( authResponse, HttpStatus.OK);
    }

    @GetMapping("/checkAuthStatus")
	public ResponseEntity<?> test( @RequestHeader("Authorization") String authotizationHeader ) {              
        String token = authotizationHeader.replace( "Bearer ", "" );
        System.out.println("TOKEN: " + token);
        String username = jwtServices.extractUsername( token );
        UserDetails userDetails = userDetailsService.loadUserByUsername( username );
        if( jwtServices.isTokenValid( token, userDetails ) ){
            return new ResponseEntity<RequestResponse>(new RequestResponse(true, "Acceso al sistema", token), HttpStatus.OK);
        }
        return new ResponseEntity<RequestResponse>(new RequestResponse(false, "Token invalido", null), HttpStatus.UNAUTHORIZED);		
	}


    @PostMapping("/saveUser")
    public ResponseEntity<?> saveUser( @RequestBody User usuario ){

        String nombres = usuario.getPerson().getNombres().toLowerCase();
        String apellidos = usuario.getPerson().getApellidos().toLowerCase();

        String[] nombres_ = nombres.split(" ");
        String[] apellidos_ = apellidos.split(" ");

        String inicialNombre = nombres_[0].substring(0, 1);
        
        String primerApellido = apellidos_[0];
        String segundoApellido = apellidos_.length > 1 ? apellidos_[1].substring(0, 1) : "";
        String mail_generate = inicialNombre + primerApellido + segundoApellido + "@mail.com";
        usuario.setEmail(mail_generate);
        System.out.println("******CORREO GENERADO: " + mail_generate );

        

        return new ResponseEntity<RequestResponse>(new RequestResponse(true, null, usuario.toString()), HttpStatus.CREATED);   
    }

}
