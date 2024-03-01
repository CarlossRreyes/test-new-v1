package com.testsecurity.services;

import com.testsecurity.entities.User;

public interface IUserService {

    public User searchUserByEmail( String email );
}
