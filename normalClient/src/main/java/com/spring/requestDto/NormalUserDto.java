package com.spring.requestDto;

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
@NoArgsConstructor
@AllArgsConstructor
public class NormalUserDto {
    //vm.user={id: null, firstName:'', lastName:'', dob:'', bio:'', nation:'', username:'', password:'',
    // address:'', email:'', agenda:'', joinDate:'', academics:'', experience: ''};
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
