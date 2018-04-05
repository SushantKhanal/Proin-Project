package com.spring.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="normal_fav_pro_users_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavProUsers {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String favProUsername;

    private String loggedInNormalUsername;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "normal_user_id")
    private NormalUser loggedInNormalUser;

    public FavProUsers(String loggedInNormalUsername, String favProUsername, NormalUser loggedInNormalUser) {
        this.loggedInNormalUsername = loggedInNormalUsername;
        this.favProUsername = favProUsername;
        this.loggedInNormalUser = loggedInNormalUser;
    }

    @Override
    public String toString() {
        return "FavProUsers{" +
                "id=" + id +
                ", favProUsername='" + favProUsername + '\'' +
                ", loggedInNormalUsername='" + loggedInNormalUsername + '\'' +
                ", loggedInNormalUser=" + loggedInNormalUser +
                '}';
    }
}
