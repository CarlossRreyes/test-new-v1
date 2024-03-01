package com.testauth.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testauth.entities.User;

public interface IUserRepository extends JpaRepository< User, Integer> {
    Optional<User> findOneByEmail( String email );
}
