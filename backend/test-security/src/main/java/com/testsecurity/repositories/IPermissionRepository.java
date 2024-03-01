package com.testsecurity.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.testsecurity.entities.Permiso;



public interface IPermissionRepository extends JpaRepository<Permiso, Integer> {
    
    @Query( nativeQuery = true, value = "EXEC SP_searchPermissionByIdUserType :id_rol")
    public List<Permiso> SP_searchPermissionByIdUserType(@Param("id_rol") Integer id_rol);
    
}
