package com.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
@Entity
@Table(name="user_academics_table")
@Getter
@Setter
@AllArgsConstructor
public class UserAcademics {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String degree;

    private String school;

    private String location;

    private Date startDate;

    private Date endDate;

    private String description;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    public UserAcademics(String username, String degree, String school, String location, Date startDate, Date endDate, String description, User user) {
        this.username = username;
        this.degree = degree;
        this.school = school;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.user = user;
    }


    public UserAcademics() {super();}

    @Override
    public String toString() {
        return "UserAcademics{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", degree='" + degree + '\'' +
                ", school='" + school + '\'' +
                ", location='" + location + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}
