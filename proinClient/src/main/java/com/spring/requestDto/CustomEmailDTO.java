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
public class CustomEmailDTO {
    private String emailId;
    private String subject;
    private String body;
}
