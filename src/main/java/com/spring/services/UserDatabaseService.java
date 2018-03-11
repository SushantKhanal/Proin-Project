package com.spring.services;

import java.util.List;

import com.spring.model.User;

public interface UserDatabaseService {

    void addUser(User p);

    void updateUser(User p);

    List<User> listUsers();

    User getUserById(Long id);

    void removeUser(Long id);

    User getUserByUsername(String username);

}
