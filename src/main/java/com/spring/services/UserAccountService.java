package com.spring.services;

import java.util.List;

import com.spring.model.User;

public interface UserAccountService {


    void updateUser(User p); //useraccount

    User getUserById(Long id); //useraccount //while updating user

}
