package com.spring.services;

import java.util.List;

import com.spring.model.User;

public interface UserDatabaseService {


    void updateUser(User p); //useraccount

    List<User> listUsers();

    User getUserById(Long id); //signin

    void removeUser(Long id);

    User getUserByUsername(String username); //signin

}
