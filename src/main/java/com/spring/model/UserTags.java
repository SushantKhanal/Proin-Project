package com.spring.model;
        import lombok.Getter;
        import lombok.Setter;
        import javax.persistence.*;

@Entity
@Table(name="users_tags_table")
@Getter
@Setter
public class UserTags {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String tags;

    private String username;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    public UserTags(String username, String tags, User user) {
        this.username = username;
        this.tags = tags;
        this.user = user;
    }

    public UserTags(Long id, String username, String tags, User user) {
        this.id = id;
        this.username = username;
        this.tags = tags;
        this.user = user;
    }

    public UserTags() {
        super();
    }

    @Override
    public String toString() {
        return "UserTags{" +
                "id=" + id +
                ", tags='" + tags + '\'' +
                ", username='" + username + '\'' +
                ", user=" + user +
                '}';
    }

}
