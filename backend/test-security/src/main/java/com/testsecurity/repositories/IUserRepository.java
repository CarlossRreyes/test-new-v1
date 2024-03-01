package com.testsecurity.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.testsecurity.entities.User;

public interface IUserRepository extends JpaRepository<User, Integer> {

    @Query( nativeQuery = true, value = "EXEC SP_GetUserByEmail :email" )
    public User SP_GetUserByEmail(@Param("email") String email);


}
