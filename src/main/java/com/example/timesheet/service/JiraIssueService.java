package com.example.timesheet.service;

import com.example.timesheet.model.Jiraissue;
import com.example.timesheet.model.Project;
import com.example.timesheet.repository.JiraIssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class JiraIssueService {
  @Autowired
  private JiraIssueRepository jiraIssueRepository;
  @Autowired
  private ProjectService projectService;


  List<Double> getIDProject(List<Project> liste) {
    List<Double> projectIdList = new ArrayList<>();
    for (Project project : liste) {
      projectIdList.add(project.getIdProject());
    }
    return projectIdList;
  }

  public List<Jiraissue> getJiraIssues(String user) {
    List<Double> projects = new ArrayList<>(getIDProject(projectService.getProjectByUser(user)));
    List<Jiraissue> listeFinale;
    //fusionner deux listes sans duplication**/
    listeFinale = Stream.of(jiraIssueRepository.findDistinctByAssigneeAndProjectInAndIssuestatusNotLike(user, projects, "6"), jiraIssueRepository.findDistinctByAssigneeNullAndProjectInAndIssuestatusNotLike(projects, "6"))
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    return listeFinale;
  }


  public List<Jiraissue> getJiraIssuesByUserAndProject(String user, Double project) {
    List<Jiraissue> listeFinale;
    //fusionner deux listes sans duplication**/
    listeFinale = Stream.of(jiraIssueRepository.findDistinctByAssigneeAndProjectAndIssuestatusNotLike(user, project, "6"), jiraIssueRepository.findDistinctByAssigneeNullAndProjectAndIssuestatusNotLike(project, "6"))
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    return listeFinale;
  }


  public List<Double> getProject(List<Jiraissue> liste) {
    List<Double> projectList = new ArrayList<>();
    for (Jiraissue project : liste) {
      projectList.add(project.getProject());
    }
    //retourner distinct projects**/
    projectList = projectList.stream().distinct().collect(Collectors.toList());
    return projectList;
  }


}
