package com.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    //        vm.user={id: null, clientType:'', username:'',address:'',email:'',agenda:'',academics: '',experience: '', marketDomain: ''};
    private Long id;

    private String clientType;

    private String username;

    private String password;

    private String address;

    private String email;

    private String agenda;

    private String academics;

    private String experience;

    private String marketDomain;

    public User() {
        id = 0L;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", clientType='" + clientType + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", agenda='" + agenda + '\'' +
                ", academics='" + academics + '\'' +
                ", experience='" + experience + '\'' +
                ", marketDomain='" + marketDomain + '\'' +
                '}';
    }
}
