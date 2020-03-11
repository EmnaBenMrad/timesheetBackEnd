package com.example.timesheet.service.DTO;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class ProjectroleDTO {

  private String id;
  private Double id_role;
  private String name;
  private String description;

  public ProjectroleDTO(Double id_role) {
    this.id_role = id_role;
  }

  public ProjectroleDTO() {

  }
}
