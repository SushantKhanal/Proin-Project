package com.spring.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="normal_profile_pic_table")
@Getter
@Setter
public class NormalProfilePic {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String picPath;

    private String username;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "normal_user_id")
    private NormalUser user;

    public NormalProfilePic(String username, String picPath, NormalUser user) {
        this.username = username;
        this.picPath = picPath;
        this.user = user;
    }

    public NormalProfilePic() {
       super();
    }

    @Override
    public String toString() {
        return "NormalProfilePic{" +
                "id=" + id +
                ", picPath='" + picPath + '\'' +
                ", username='" + username + '\'' +
                ", user=" + user +
                '}';
    }


}
