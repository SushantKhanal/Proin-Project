package com.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
@Entity
@Table(name="user_experience_table")
@Getter
@Setter
@AllArgsConstructor
public class UserExperience {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String title;

    private String company;

    private String location;

    private String startDate;

    private String endDate;

    private String description;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    public UserExperience(String username, String title, String company,
                          String location, String startDate, String endDate,
                          String description, User user) {
        this.username = username;
        this.title = title;
        this.company = company;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.user = user;
    }

    public UserExperience() {super();}

    @Override
    public String toString() {
        return "UserExperience{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", company='" + company + '\'' +
                ", location='" + location + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}
