package com.example.timesheet.service;

import com.example.timesheet.model.ProjectExclusiveTETD;
import com.example.timesheet.repository.ProjectExclusiveTETDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectExclusiveTETDService {
    @Autowired
    private ProjectExclusiveTETDRepository projectExclusiveTETDRepository;

    /***Afficher les projets non imputables**/
    public List<ProjectExclusiveTETD> getAll() {
        return projectExclusiveTETDRepository.findAll();
    }

    /***Générate IdProjectExclusive**/
    public Double getNext() {
        ProjectExclusiveTETD last = projectExclusiveTETDRepository.findTopByOrderByIdDesc();
        Double lastNum = last.getIdProjectExclusive();
        ProjectExclusiveTETD next = new ProjectExclusiveTETD(lastNum + 1);
        return next.getIdProjectExclusive();
    }


    /***Ajouter un projet non imputable***/
    public ProjectExclusiveTETD addProject(ProjectExclusiveTETD projectExclusiveTETD) {
        ProjectExclusiveTETD projectExclusiveTETDAdded = new ProjectExclusiveTETD();
        projectExclusiveTETDAdded.setIdProjectExclusive(getNext());
        projectExclusiveTETDAdded.setPname(projectExclusiveTETD.getPname());
        return projectExclusiveTETDRepository.save(projectExclusiveTETDAdded);
    }

    /***Supprimer un projet non imputable de la liste***/
    public void deleteProject(String id) {
        projectExclusiveTETDRepository.deleteProjectExclusiveTETDById(id);
    }

}
