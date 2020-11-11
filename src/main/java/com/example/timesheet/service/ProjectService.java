package com.example.timesheet.service;

import com.example.timesheet.model.*;
import com.example.timesheet.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectRoleActorService projectRoleActorService;
    @Autowired
    ProjectService projectService;
    @Autowired
    JiraIssueService jiraIssueService;


    public List<Project> getProjectByIdList(List<Double> pid) {
        return projectRepository.findByIdProjectIn(pid);
    }

    public List<Project> getProjectList(String user) {
        return projectService.getProjectByIdList(jiraIssueService.getProject(jiraIssueService.getJiraIssues(user)));
    }

    //List<Project> projectsSortedByName;
    public List<String> projectList(List<Project> liste) {
        List<String> projectsList = new ArrayList<>();
        liste.sort(Comparator.comparing(Project::getPname));
        for (Project project : liste) {
            projectsList.add(project.getPname());
        }
        return projectsList;
    }


    public List<Project> getProjectByUser(String user) {
        return projectRepository.findByIdProjectIn(projectRoleActorService.projectIdList(projectRoleActorService.getProjectByUser(user)));

    }

    public List<Project> getAll() {
        return projectRepository.findAll();
    }
}
