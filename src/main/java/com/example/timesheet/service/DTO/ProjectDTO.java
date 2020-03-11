package com.example.timesheet.service.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ProjectDTO {
  private String id;
  private Double idProject;
  private String pname;
  private String url;
  private String lead;
  private String pkey;
  private Double pcounter;
  private Double assigneetype;

}
