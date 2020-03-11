package com.example.timesheet.service.DTO;

import com.example.timesheet.model.InvAppliUserRole;
import com.example.timesheet.model.Jiraissue;

import java.util.Date;

public class ImputationDTO {
  private String id;
  private int idImputation;
  private int Project;
  private int user;
  private String Date;
  private Double imputation;
  private Double raf;
  private int validation;
  private String commentaire;
  private Date dateImputation;
  private Date dateValidation;
  private String validaPar;
  private Jiraissue jiraissue;
  private InvAppliUserRole invAppliUserRole;
}
