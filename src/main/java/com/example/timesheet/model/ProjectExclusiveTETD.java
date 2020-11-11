package com.example.timesheet.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "tetdProjectExclusive")

public class ProjectExclusiveTETD {
  @Id
  private String id;
  private Double idProjectExclusive;
  private String pname;


  public ProjectExclusiveTETD() {
  }

  public ProjectExclusiveTETD(Double idProjectExclusive) {
    this.idProjectExclusive = idProjectExclusive;
  }
}
