package com.testcloud.exception;
import javax.naming.AuthenticationException;

public class JwtTokenMalformedException extends AuthenticationException{
    
    public JwtTokenMalformedException( String msg ){
        super(msg);
    }
}