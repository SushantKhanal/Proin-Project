package com.spring.model;
        import lombok.Getter;
        import lombok.Setter;

        import javax.persistence.*;
//0 is deleted, while 1 is active

@Entity
@Table(name="userSignUpRequest_status_table")
@Getter
@Setter
public class UserSignUpRequestStatus {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Integer status; //either 0 or 1

    private String username;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userSignUpRequest_id")
    private UserSignUpRequest userSignUpRequest;

    public UserSignUpRequestStatus(Long id, Integer status, String username, UserSignUpRequest userSignUpRequest) {
        this.id = id;
        this.username = username;
        this.status = status;
        this.userSignUpRequest = userSignUpRequest;
    }

    public UserSignUpRequestStatus(Integer status, String username, UserSignUpRequest userSignUpRequest) {
        this.username = username;
        this.status = status;
        this.userSignUpRequest = userSignUpRequest;
    }

    public UserSignUpRequestStatus() {
        super();
    }

    @Override
    public String toString() {
        return "UserSignUpRequestStatus{" +
                "id=" + id +
                ", status=" + status +
                ", username='" + username + '\'' +
                ", userSignUpRequest=" + userSignUpRequest +
                '}';
    }

}
