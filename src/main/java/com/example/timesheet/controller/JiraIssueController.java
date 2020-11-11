package com.example.timesheet.controller;


import com.example.timesheet.model.Jiraissue;
import com.example.timesheet.service.JiraIssueService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class JiraIssueController {


  @Autowired
  JiraIssueService jiraIssueService;

  @ApiOperation(value = "Lister Issue")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/jiraIssueList/{assignee}/{project}")
  public List<Jiraissue> jiraIssueList(@PathVariable String assignee, @PathVariable Double project) {
    return jiraIssueService.getJiraIssuesByUserAndProject(assignee, project);
  }
  /*@ApiOperation(value = "Lister projects")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/jiraIssueList/{assignee}")
  public List<Double> projectIDList(@PathVariable String assignee) {
    return jiraIssueService.getProject(jiraIssueService.getJiraIssues(assignee));
  }*/


}
