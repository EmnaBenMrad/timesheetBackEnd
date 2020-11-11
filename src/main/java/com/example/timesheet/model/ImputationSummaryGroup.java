package com.example.timesheet.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ImputationSummaryGroup {
  private String userName;
  private String project;
  private String jiraissueList;
  private List<String> dateList;
  private List<Double> imputationList;
  private Map<String, String> joursMois;
  private String pkeyIssue;
  //private float averagePrice;
  private Double totalImputations;

  public void setJoursMois(Map<String, String> joursMois) {
    this.joursMois = joursMois;
  }

  public ImputationSummaryGroup(String userName, String project, String jiraissueList, List<String> dateList, List<Double> imputationList, Map<String, String> joursMois, String pkeyIssue, Double totalImputations) {
    this.userName = userName;
    this.project = project;
    this.jiraissueList = jiraissueList;
    this.dateList = dateList;
    this.imputationList = imputationList;
    this.joursMois = joursMois;
    this.pkeyIssue = pkeyIssue;
    this.totalImputations = totalImputations;
  }
}
