package com.example.timesheet.service;

import com.example.timesheet.model.Jiraissue;
import com.example.timesheet.repository.JiraIssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JiraIssueService {
  @Autowired
  private JiraIssueRepository jiraIssueRepository;

  public List<Jiraissue> getAll() {
    return jiraIssueRepository.findAll();
  }
}
