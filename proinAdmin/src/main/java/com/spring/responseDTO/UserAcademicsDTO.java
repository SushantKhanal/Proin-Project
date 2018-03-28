package com.spring.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAcademicsDTO {
    private Long id;
    private String username;
    private String degree;
    private String school;
    private String location;
    private Date startDate;
    private Date endDate;
    private String description;
}