package com.app.test.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.test.entities.Usuario;

public interface IUserRepository extends JpaRepository< Usuario, Integer> {
    
    @Query( nativeQuery = true, value = "EXEC SP_ListUser :state" )
    List<Usuario> SP_ListUser(@Param("state") String state);
}
