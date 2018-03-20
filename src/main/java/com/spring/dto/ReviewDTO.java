package com.spring.dto;

        import lombok.Getter;
        import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {
    private String loggedInUsername;
    private String otherUsername;
    private String review;
}
