package com.spring.services;

import com.spring.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
@Service("userService")

public class UserServiceImpl implements UserService{
    private static final AtomicLong counter = new AtomicLong();

    private List<User> users = new ArrayList<User>();

    public void saveUser(User user) {
        user.setId(counter.incrementAndGet());
        users.add(user);
    }
    public User findByName(String name) {
        if (users != null && !users.isEmpty()) {
            for (User user : users) {
                if (user.getUsername().equalsIgnoreCase(name)) {
                    return user;
                }
            }
        }
        return null;
    }

    public List<User> findAllUsers() {
        return users;
    }

    public boolean isUserExist(User user) {
        return findByName(user.getUsername())!=null;
    }
}
