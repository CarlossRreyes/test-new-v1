package com.app.test.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.test.entities.Usuario;
import com.app.test.repositories.IUserRepository;
import com.app.test.services.IUsuarioService;

import jakarta.transaction.Transactional;

@Service
public class UsuarioImpl implements IUsuarioService {

    @Autowired
    private IUserRepository usersRepository;

    @Override
    @Transactional
    public List<Usuario> listUsers( String state ) {
        return this.usersRepository.SP_ListUser( state );
    }
    
}
