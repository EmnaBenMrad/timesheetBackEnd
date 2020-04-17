package com.example.timesheet.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "project")
public class Project {
  @Id
  private String id;
  private Double idProject;
  private String pname;
  private String url;
  private String lead;
  private String description;
  private String pkey;
  private Double pcounter;
  private Double assigneetype;

}
