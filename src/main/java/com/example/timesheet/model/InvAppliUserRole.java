package com.example.timesheet.model;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "inv_appli_user_role")
public class InvAppliUserRole {
  @Id
  private String id;
  private Project project;
  private Projectrole projectrole;
  private Userbase userbase;

}
