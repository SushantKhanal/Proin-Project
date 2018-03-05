package com.spring.services;

import com.spring.model.User;

import java.util.List;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
public interface UserService {

    User findById(long id);

    void saveUser(User user);

    public void updateUser(User user);

    User findByName(String name);

    List<User> findAllUsers();

    public boolean isUserExist(User user);

}
