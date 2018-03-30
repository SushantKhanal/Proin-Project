package com.spring.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
//0 is deleted, while 1 is active

@Entity
@Table(name="user_status_table")
@Getter
@Setter
public class UserStatus {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Integer status; //either 0 or 1

    private String username;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    public UserStatus(Long id, Integer status, String username, User user) {
        this.id = id;
        this.username = username;
        this.status = status;
        this.user = user;
    }

    public UserStatus(Integer status, String username, User user) {
        this.username = username;
        this.status = status;
        this.user = user;
    }

    public UserStatus() {
        super();
    }

    @Override
    public String toString() {
        return "UserStatus{" +
                "id=" + id +
                ", status=" + status +
                ", username='" + username + '\'' +
                ", user=" + user +
                '}';
    }
}
