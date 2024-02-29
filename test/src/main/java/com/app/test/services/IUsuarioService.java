package com.app.test.services;

import java.util.List;

import com.app.test.entities.Usuario;

public interface IUsuarioService {
    public List<Usuario> listUsers( String state );
}
