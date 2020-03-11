package com.example.timesheet.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "projectrole")
public class Projectrole {
  //
  //@Id
  //@Indexed(unique = true)
  @Id
  private String id;
  //@Indexed
  private Double id_role;
  private String name;
  private String description;

  public Projectrole(Double id_role) {
    this.id_role = id_role;
  }

  public Projectrole() {
  }

//  public Double getIdRole() {
//    return idRole;
//  }
//
//  public void setIdRole(Double idRole) {
//    this.idRole = idRole;
//  }


  public Double getId_role() {
    return id_role;
  }

  public void setId_role(Double id_role) {
    this.id_role = id_role;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Projectrole{" +
      "id='" + id + '\'' +
      ", id_role=" + id_role +
      ", name='" + name + '\'' +
      ", description='" + description + '\'' +
      '}';
  }
}
