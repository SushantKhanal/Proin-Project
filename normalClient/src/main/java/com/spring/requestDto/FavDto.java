package com.spring.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class FavDto {
    private String loggedInNormalUser;
    private String favProUser;
}
