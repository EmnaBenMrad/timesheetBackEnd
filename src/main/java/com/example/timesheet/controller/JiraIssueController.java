package com.example.timesheet.controller;


import com.example.timesheet.model.Jiraissue;
import com.example.timesheet.model.Projectrole;
import com.example.timesheet.repository.JiraIssueRepository;
import com.example.timesheet.repository.ProjectRoleRepository;
import com.example.timesheet.service.ProjectRoleService;
import com.example.timesheet.service.JiraIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api2")
public class JiraIssueController {
  @Autowired
  JiraIssueRepository jiraIssueRepository;

  @Autowired
  JiraIssueService jiraIssueService;

  @GetMapping("/read2")
  public List<Jiraissue> read() {
   
    //System.out.println("aaaaaa"+jiraIssueRepository.findById((long) 10003));
    return jiraIssueRepository.findAll();
    //return jiraIssueService.getAll();
  }
}
