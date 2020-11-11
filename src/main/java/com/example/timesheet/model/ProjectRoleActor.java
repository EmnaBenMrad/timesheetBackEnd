package com.example.timesheet.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "projectroleactor")
public class ProjectRoleActor {
    @Id
    private String id;
    private Double idProjectRoleActor;
    private Double projectId;
    private Double projectRoleId;
    private String roleType;
    private String roleTypeParameter;


}
