package com.spring.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="User_reviews_table")
@Getter
@Setter
public class UserReviews {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String otherUsername;

    private String loggedInUsername;

    private String review;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User loggedInUser;

    public UserReviews(String loggedInUsername,String otherUsername, String review, User user) {
        this.loggedInUsername = loggedInUsername;
        this.otherUsername = otherUsername;
        this.review = review;
        this.loggedInUser = user;
    }

    public UserReviews(Long id, String loggedInUsername,String otherUsername, String review, User user) {
        this.id = id;
        this.loggedInUsername = loggedInUsername;
        this.otherUsername = otherUsername;
        this.review = review;
        this.loggedInUser = user;
    }

    public UserReviews() {
        super();
    }

    @Override
    public String toString() {
        return "UserReviews{" +
                "id=" + id +
                ", otherUsername='" + otherUsername + '\'' +
                ", loggedInUsername='" + loggedInUsername + '\'' +
                ", review='" + review + '\'' +
                ", loggedInUser=" + loggedInUser +
                '}';
    }
}

