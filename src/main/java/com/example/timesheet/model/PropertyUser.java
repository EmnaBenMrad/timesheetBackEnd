package com.example.timesheet.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "propertyUser")
public class PropertyUser {

    @Id
    private String id;
    private Double idPropertyUser;
    private String propertyvalue;


}
