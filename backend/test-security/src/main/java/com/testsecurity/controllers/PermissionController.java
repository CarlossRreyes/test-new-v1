package com.testsecurity.controllers;


import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testsecurity.entities.Permiso;
import com.testsecurity.entities.User;
import com.testsecurity.security.services.JwtServices;
import com.testsecurity.services.IPermissionService;
import com.testsecurity.services.IUserService;
import com.testsecurity.utils.RequestResponse;
import com.testsecurity.utils.ResponseMessage;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/security/permission")
@RequiredArgsConstructor
public class PermissionController {

    @Autowired
    private JwtServices jwtServices;

    
    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IUserService userService;

    @GetMapping("/userAuthenticate")
    public ResponseEntity<?> getUserAuthenticate( @RequestHeader("Authorization") String authotizationHeader ){
        String token = authotizationHeader.replace( "Bearer ", "" ); 
        User user = new User();
        List<Permiso> listPermission = new ArrayList<>();
        
        String email = jwtServices.extractUsername( token );
        try {
            user = userService.searchUserByEmail(email);
            listPermission = permissionService.searchPermissionByIdUserType( user.getRol().getId() );
            
        } catch (DataAccessException e) {
            return new ResponseEntity<RequestResponse>(
                new RequestResponse(false, ResponseMessage.ERROR_QUERY, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()), null), 
                HttpStatus.INTERNAL_SERVER_ERROR
            );  
        }

        return new ResponseEntity<RequestResponse>(
            new RequestResponse(true, ResponseMessage.DATA_SUCCESSFULLY_RECOVERED, "", listPermission), 
            HttpStatus.OK
        );  
           
    }


    
    
}
