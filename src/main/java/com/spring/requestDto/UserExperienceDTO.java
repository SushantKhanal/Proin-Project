package com.spring.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
      //  vm.experience={id: null, username:'', title:'', company:'', location:'', startDate:'', endDate:'', description:''};
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
