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
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private String loggedInUsername;
    private String otherUsername;
    private String review;
    private Integer rating;
}
