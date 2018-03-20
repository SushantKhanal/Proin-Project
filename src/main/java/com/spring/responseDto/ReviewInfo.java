package com.spring.responseDto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
@Getter
@Setter
public class ReviewInfo {
    private String loggedInUsername;
    private String reviewedUsername;
    private String review;
    public ReviewInfo(String loggedInUsername, String reviewedUsername, String review) {
        this.loggedInUsername = loggedInUsername;
        this.reviewedUsername = reviewedUsername;
        this.review = review;
    }
}
