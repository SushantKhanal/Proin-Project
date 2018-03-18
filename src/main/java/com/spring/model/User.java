package com.spring.model;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

@Entity
@Table(name="users_table")

@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String dob;

    private String bio;

    private String nation;

    private String clientType;

    private String username;

    private String password;

    private String address;

    private String email;

    private String agenda;

    private String joinDate;

    private String academics;

    private String experience;

    private String marketDomain;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob='" + dob + '\'' +
                ", bio='" + bio + '\'' +
                ", nation='" + nation + '\'' +
                ", clientType='" + clientType + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", agenda='" + agenda + '\'' +
                ", joinDate='" + joinDate + '\'' +
                ", academics='" + academics + '\'' +
                ", experience='" + experience + '\'' +
                ", marketDomain='" + marketDomain + '\'' +
                '}';
    }
}