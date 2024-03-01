package com.testsecurity.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testsecurity.entities.User;
import com.testsecurity.repositories.IUserRepository;
import com.testsecurity.services.IUserService;



@Service
public class IUserServiceImpl implements IUserService  {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public User searchUserByEmail(String email) {
        return userRepository.SP_GetUserByEmail(email);
    }

  
}
