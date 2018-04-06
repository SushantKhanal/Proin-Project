package com.spring.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
@Entity
@Table(name="normal_follow_request_table")
@Getter
@Setter
@NoArgsConstructor
public class NormalFollowRequest {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String fromNormalUsername;
    private String toProUsername;
    private String message;

    public NormalFollowRequest(Long id, String fromNormalUsername, String toProUsername, String message) {
        this.id = id;
        this.fromNormalUsername = fromNormalUsername;
        this.toProUsername = toProUsername;
        this.message = message;
    }

    public NormalFollowRequest(String fromNormalUsername, String toProUsername, String message) {
        this.fromNormalUsername = fromNormalUsername;
        this.toProUsername = toProUsername;
        this.message = message;
    }

    @Override
    public String toString() {
        return "NormalFollowRequest{" +
                "id=" + id +
                ", fromNormalUsername='" + fromNormalUsername + '\'' +
                ", toProUsername='" + toProUsername + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
