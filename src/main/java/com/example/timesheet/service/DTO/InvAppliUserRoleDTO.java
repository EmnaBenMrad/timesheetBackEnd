package com.example.timesheet.service.DTO;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "inv_appli_user_role")
public class InvAppliUserRoleDTO {

  private String id;
  private ProjectDTO project;
  private ProjectroleDTO projectrole;
  private UserbaseDTO userbase;

}
