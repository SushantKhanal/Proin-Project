package com.spring.requestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavUserDTO {
    private String loggedInUser;
    private String favUser;
}
