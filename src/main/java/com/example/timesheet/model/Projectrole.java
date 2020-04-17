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
  private Double idRole;
  private String name;
  private String description;
  public Projectrole(Double idRole) {
    this.idRole = idRole;
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


  public Double getIdRole() {
    return idRole;
  }

  public void setIdRole(Double idRole) {
    this.idRole = idRole;
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
      ", idRole=" + idRole +
      ", name='" + name + '\'' +
      ", description='" + description + '\'' +
      '}';
  }
}
