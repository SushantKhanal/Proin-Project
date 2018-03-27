package com.spring.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="fav_users_table")
@Getter
@Setter
public class FavUsers {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String favUsername;

    private String loggedInUsername;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User loggedInUser;

    public FavUsers(String loggedInUsername,String favUsername, User user) {
        this.loggedInUsername = loggedInUsername;
        this.favUsername = favUsername;
        this.loggedInUser = user;
    }

    public FavUsers() {
        super();
    }

    @Override
    public String toString() {
        return "FavUsers{" +
                "id=" + id +
                ", favUsername='" + favUsername + '\'' +
                ", loggedInUsername='" + loggedInUsername + '\'' +
                ", loggedInUser=" + loggedInUser +
                '}';
    }
}
