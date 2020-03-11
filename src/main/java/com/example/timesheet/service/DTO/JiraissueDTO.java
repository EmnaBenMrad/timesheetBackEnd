package com.example.timesheet.service.DTO;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
public class JiraissueDTO {

  private String ID;
  private Double idIssue;

  private String pkey;
  private Double project;
  private String reporter;
  private String assignee;
  private String issuetype;
  private String summary;
  private String description;
  private String environment;
  private String priority;
  private String resolution;
  private String issuestatus;
  private Date created;
  private Date updated;
  private Date duedate;
  private Double votes;
  private Double timeOriginalEstimate;
  private Double timeEstimate;
  private Double timeSpent;
  private Double workflowID;
  private Double security;
  private Double fixFor;
  private Double component;


}
