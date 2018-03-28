package com.spring.responseDTO;

import lombok.Getter;
import lombok.Setter;

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
    private Integer rating;
    public ReviewInfo(String loggedInUsername, String reviewedUsername, String review, Integer rating) {
        this.loggedInUsername = loggedInUsername;
        this.reviewedUsername = reviewedUsername;
        this.review = review;
        this.rating = rating;
    }
}
