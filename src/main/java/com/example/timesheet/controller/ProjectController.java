package com.example.timesheet.controller;

import com.example.timesheet.model.Project;
import com.example.timesheet.service.ProjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class ProjectController {

  @Autowired
  ProjectService projectService;


  @ApiOperation(value = "Lister projets ")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/readProjets")
  public List<Project> readProjects() {
    return projectService.getAll();
  }

  @ApiOperation(value = "Lister projets ")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/projetsList")
  List<String> projetsNonImputableList() {
    return projectService.projectList(readProjects());
  }


  @ApiOperation(value = "Lister all projects")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/projectsList/{user}")
  public List<Project> projectsLists(@PathVariable String user) {
    return projectService.getProjectList(user);
  }


}
