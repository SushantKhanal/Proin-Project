package com.spring.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="profile_pic_table")
@Getter
@Setter
public class UserProfilePic {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String picPath;

    private String username;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    public UserProfilePic(String username,String picPath, User user) {
        this.username = username;
        this.picPath = picPath;
        this.user = user;
    }

    public UserProfilePic() {
       super();
    }

    @Override
    public String toString() {
        return "UserProfilePic{" +
                "id=" + id +
                ", picPath='" + picPath + '\'' +
                ", username='" + username + '\'' +
                ", user=" + user +
                '}';
    }
}
