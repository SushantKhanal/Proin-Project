package com.spring.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {
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
}
