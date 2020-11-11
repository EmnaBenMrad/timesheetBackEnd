package com.example.timesheet.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "userbase")
public class Userbase {
    @Id
    private String id;
    private Double idUser;
    private String username;
    private String passwordHash;

    public Userbase(String username, String encode) {
    }

    public Userbase(Double idUser, String username, String passwordHash) {
        this.idUser = idUser;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public Userbase() {

    }


}
