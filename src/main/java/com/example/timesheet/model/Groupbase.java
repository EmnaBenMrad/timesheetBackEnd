package com.example.timesheet.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "groupbase")
public class Groupbase {

    @Id
    private String id;
    private Double idGroupbase;
    private String groupname;
    private int statut;

    public Groupbase(Double idGroupbase) {
        this.idGroupbase = idGroupbase;
    }

    public Groupbase() {
    }
}
