package com.spring.services;

import com.spring.model.User;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
@Service("userService")

public class UserServiceImpl implements UserService{
    private static final AtomicLong counter = new AtomicLong();

    private static List<User> users;


    public List<User> findAllUsers() {
        return users;
    }


    public User findByName(String name) {
        for(User user : users){
            if(user.getUsername().equalsIgnoreCase(name)){
                return user;
            }
        }
        return null;
    }

    public void saveUser(User user) {
        user.setId(counter.incrementAndGet());
        users.add(user);
    }


    public boolean isUserExist(User user) {
        return findByName(user.getUsername())!=null;
    }
}
