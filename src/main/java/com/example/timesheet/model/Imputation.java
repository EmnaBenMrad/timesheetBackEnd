package com.example.timesheet.model;

import java.util.Date;
import java.util.List;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "imputation")
public class Imputation {

  @Id
  private String id;
  private int idImputation;
  private String Date;
  private Double imputation;
  private Double raf;
  private int validation;
  private String commentaire;
  private Date dateImputation;
  private Date dateValidation;
  private String validaPar;
  private Jiraissue jiraissue;
  private InvAppliUserRole invappliuserrole;


  public Imputation(int i) {
    this.idImputation = i;
  }

  public Imputation() {
  }
}
