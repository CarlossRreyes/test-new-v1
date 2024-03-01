package com.testsecurity.services.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testsecurity.entities.Permiso;
import com.testsecurity.repositories.IPermissionRepository;
import com.testsecurity.services.IPermissionService;

import jakarta.transaction.Transactional;


@Service
public class IPermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionRepository permissionRepository;

    // @Autowired
    // private IMenuService menuService;

    @Override
    @Transactional
    public List<Permiso> searchPermissionByIdUserType(Integer id_user_type) {
        // return this.permissionRepository.searchPermissionByIdUserType(id_user_type);
        List<Permiso> listPermission = this.permissionRepository.SP_searchPermissionByIdUserType(id_user_type);
        
        // for (Permission p : listPermission) {            
        //     List<Menu> listaMenuParentByChild = menuService.searchMenuByIdMenuParentId( p.getMenu().getId_menu() );
        //     p.getMenu().setChild(listaMenuParentByChild);       
                 
        // }
        return listPermission;
    }
    
}