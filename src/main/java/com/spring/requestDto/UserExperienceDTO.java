package com.spring.requestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// vm.experience={id: null, username:'', title:'', company:'', location:'', startDate:'', endDate:'', description:''};

public class UserExperienceDTO {
    private String username;
    private String title;
    private String company;
    private String location;
    private String startDate;
    private String endDate;
    private String description;
}
