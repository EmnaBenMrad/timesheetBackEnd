package com.example.timesheet.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "membershipbase")
public class Membershipbase {
    @Id
    private String id;
    private Double idMembershipbase;
    // private String groupName;
    private String userName;
    private Groupbase groupbase;
}
