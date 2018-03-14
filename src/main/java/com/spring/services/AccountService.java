package com.spring.services;

import com.spring.model.User;

public interface AccountService {


    void updateUser(User p); //useraccount

    User getUserById(Long id); //useraccount //while updating user

}
