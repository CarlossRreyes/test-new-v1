package com.app.test.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.test.entities.Persona;
import com.app.test.entities.Usuario;
import com.app.test.services.IUsuarioService;
import com.app.test.utils.RequestResponse;
import com.app.test.utils.ResponseMessage;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class Hello_Controller {

     @Autowired
	private IUsuarioService usersService;

    @GetMapping("/hola")
    public String  initApp(){
        return "Hola Mundo, que Dios nos ayude JEJE";
    }

    @GetMapping("/list")
	public ResponseEntity<?> getAllUser(){	
        
        List<Usuario> categoryList = new ArrayList<>();        
        try {
            categoryList = usersService.listUsers("A");
        } catch (DataAccessException e) {
            return new ResponseEntity<RequestResponse>(
                new RequestResponse(false, ResponseMessage.ERROR_QUERY, null, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage())), 
                HttpStatus.INTERNAL_SERVER_ERROR );           
        }
        if( categoryList == null || categoryList.size() == 0 ){            
            return new ResponseEntity<RequestResponse>(
                new RequestResponse(false, ResponseMessage.NO_DATA_FOUND, null, null), 
                HttpStatus.OK );    
        }
        return new ResponseEntity<RequestResponse>( 
            new RequestResponse(
                true, 
                ResponseMessage.DATA_SUCCESSFULLY_RECOVERED, 
                null, 
                categoryList
                ), 
            HttpStatus.OK
        );
	}

    @PostMapping("/save")
    public ResponseEntity<?> saveUser( @RequestBody Usuario usuario ){

        String nombres = usuario.getPerson().getNombres().toLowerCase();
        String apellidos = usuario.getPerson().getApellidos().toLowerCase();

        String[] nombres_ = nombres.split(" ");
        String[] apellidos_ = apellidos.split(" ");

        String inicialNombre = nombres_[0].substring(0, 1);
        
        String primerApellido = apellidos_[0];
        String segundoApellido = apellidos_.length > 1 ? apellidos_[1].substring(0, 1) : "";
        String mail_generate = inicialNombre + primerApellido + segundoApellido + "@mail.com";
        usuario.setEmail(mail_generate);
        System.out.println("******CORREO: " + mail_generate );

        return new ResponseEntity<RequestResponse>(new RequestResponse(true, ResponseMessage.DATA_SUCCESSFULLY_RECORDED, null, usuario.toString()), HttpStatus.CREATED);   
    }


    
}
