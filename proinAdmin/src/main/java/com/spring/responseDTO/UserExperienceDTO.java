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
public class UserExperienceDTO {
    private Long id;
    private String username;
    private String title;
    private String company;
    private String location;
    private Date startDate;
    private Date endDate;
    private String description;
}
