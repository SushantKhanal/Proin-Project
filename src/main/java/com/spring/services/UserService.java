package com.spring.services;

import com.spring.model.User;

import java.util.List;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
public interface UserService {

    void saveUser(User user);

    public User findByName(String name);

    List<User> findAllUsers();

    public boolean isUserExist(User user);

}
