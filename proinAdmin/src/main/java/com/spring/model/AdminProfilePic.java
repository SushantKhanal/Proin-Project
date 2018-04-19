package com.spring.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="admin_profile_pic_table")
@Getter
@Setter
@AllArgsConstructor
public class AdminProfilePic {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String picPath;

    private String username;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    public AdminProfilePic(String username, String picPath, Admin admin) {
        this.username = username;
        this.picPath = picPath;
        this.admin = admin;
    }

    public AdminProfilePic() {
       super();
    }

    @Override
    public String toString() {
        return "AdminProfilePic{" +
                "id=" + id +
                ", picPath='" + picPath + '\'' +
                ", username='" + username + '\'' +
                ", admin=" + admin +
                '}';
    }
}
