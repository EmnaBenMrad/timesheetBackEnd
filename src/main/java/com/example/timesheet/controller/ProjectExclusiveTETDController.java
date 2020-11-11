package com.example.timesheet.controller;


import com.example.timesheet.model.ProjectExclusiveTETD;
import com.example.timesheet.service.ProjectExclusiveTETDService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")

public class ProjectExclusiveTETDController {
    @Autowired
    ProjectExclusiveTETDService projectExclusiveTETDService;
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value = "Lister projets non imputables")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/projetsImpu")
    List<ProjectExclusiveTETD> projetsNonImputableList() {
        return projectExclusiveTETDService.getAll();
    }

    @ApiOperation(value = "Creer nouveau projet non imputble")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/addProjet", method = RequestMethod.POST)
    ProjectExclusiveTETD addProjetNonImputable(@RequestBody ProjectExclusiveTETD projectExclusiveTETD) {
        logger.info("processing authentication for '{}'", "list articles");
        return projectExclusiveTETDService.addProject(projectExclusiveTETD);
    }

    @ApiOperation(value = "Delete projetNonImputble by ID")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/deleteProjet/{id}", method = RequestMethod.DELETE)
    void deleteImputation(@PathVariable String id) {
        projectExclusiveTETDService.deleteProject(id);
    }
}
