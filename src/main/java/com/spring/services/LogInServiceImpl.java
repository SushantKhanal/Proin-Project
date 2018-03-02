package com.spring.services;

import com.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
@Service("logInService")
public class LogInServiceImpl implements LogInService{
    @Autowired
    UserService userService;
    public List findAllUsers() {
        return userService.findAllUsers();
    }

    public User matchUser(String username, String password){
        List<User> users = userService.findAllUsers();
        //Boolean ifMatched = false;
        for (User element : users) {

            if(username.equals(element.getUsername()) && password.equals(element.getPassword())){
                //ifMatched = true;
                return element;
            }
        }
        //if(ifMatched == false) {
            return new User();
        //}
    }

}
