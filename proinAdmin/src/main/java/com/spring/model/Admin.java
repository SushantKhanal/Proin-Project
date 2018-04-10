package com.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="admins_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String dob;

    private String bio;

    private String nation;

    private String username;

    private String password;

    private String address;

    private String email;

    private String agenda;

    private String joinDate;

    private String academics;

    private String experience;

    private Long status;

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob='" + dob + '\'' +
                ", bio='" + bio + '\'' +
                ", nation='" + nation + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", agenda='" + agenda + '\'' +
                ", joinDate='" + joinDate + '\'' +
                ", academics='" + academics + '\'' +
                ", experience='" + experience + '\'' +
                ", status=" + status +
                '}';
    }

}
