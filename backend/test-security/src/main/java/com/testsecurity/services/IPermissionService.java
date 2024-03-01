package com.testsecurity.services;

import java.util.List;

import com.testsecurity.entities.Permiso;






public interface IPermissionService {

    public List<Permiso> searchPermissionByIdUserType( Integer id_user_type );
    
}

