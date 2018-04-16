package com.spring.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="user_documents_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDocuments {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String docPath;

    private String username;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    public UserDocuments(String username,String docPath, User user) {
        this.username = username;
        this.docPath = docPath;
        this.user = user;
    }

}
