package com.example.timesheet.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "userbasestatus")
public class Userbasestatus {
    @Id
    private String id;
    private Double idUserStatus;
    private Double user;
    private Double status;
}
