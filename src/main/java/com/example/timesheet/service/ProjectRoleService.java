package com.example.timesheet.service;


import com.example.timesheet.model.Projectrole;
import com.example.timesheet.repository.ProjectRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectRoleService {
  @Autowired
  private ProjectRoleRepository projectRoleRepository;

  public List<Projectrole> getAll() {
    return projectRoleRepository.findAll();
  }


  public Projectrole addProjectRole(Projectrole projectrole) {
    return projectRoleRepository.save(projectrole);
  }

  public void updateProjectRole(Projectrole projectrole) {
    projectRoleRepository.save(projectrole);
  }

  public Double getNext() {
    //Projectrole last = projectRoleRepository.findTopByOrderByIdDesc();
    Projectrole last = projectRoleRepository.findTopByOrderById_roleDesc();
    Double lastNum = last.getIdRole();
    Projectrole next = new Projectrole(lastNum + 1);
    return next.getIdRole();
  }
}
