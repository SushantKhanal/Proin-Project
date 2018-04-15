package com.spring.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Normal_user_reviews_table")
@Getter
@Setter
public class NormalUserReviews {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String otherUsername;

    private String loggedInUsername;

    private String review;

    private Integer rating;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "normal_user_id")
    private NormalUser loggedInNormalUser;

    public NormalUserReviews(String loggedInUsername, String otherUsername, String review, Integer rating, NormalUser normalUser) {
        this.loggedInUsername = loggedInUsername;
        this.otherUsername = otherUsername;
        this.review = review;
        this.rating = rating;
        this.loggedInNormalUser = normalUser;
    }

    public NormalUserReviews(Long id, String loggedInUsername, String otherUsername, String review, Integer rating, NormalUser normalUser) {
        this.id = id;
        this.loggedInUsername = loggedInUsername;
        this.otherUsername = otherUsername;
        this.review = review;
        this.rating = rating;
        this.loggedInNormalUser = normalUser;
    }

    public NormalUserReviews() {
        super();
    }

    @Override
    public String toString() {
        return "NormalUserReviews{" +
                "id=" + id +
                ", otherUsername='" + otherUsername + '\'' +
                ", loggedInUsername='" + loggedInUsername + '\'' +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                ", loggedInNormalUser=" + loggedInNormalUser +
                '}';
    }
}

