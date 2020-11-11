package com.example.timesheet.service;


import com.example.timesheet.model.ProjectExclusiveTETD;
import com.example.timesheet.model.ProjectRoleActor;

import com.example.timesheet.repository.ProjectRoleActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProjectRoleActorService {
  @Autowired
  private ProjectRoleActorRepository projectRoleActorRepository;
  @Autowired
  private ProjectExclusiveTETDService projectExclusiveTETDService;
  @Autowired
  private MembershipbaseService membershipbaseService;
  //List<ProjectExclusiveTETD> liste= projectExclusiveTETDService.getAll();
  List<Double> pidList = new ArrayList<>();

  List<Double> pidList(List<ProjectExclusiveTETD> liste) {
    for (ProjectExclusiveTETD projectExclusiveTETD : liste) {
      pidList.add(projectExclusiveTETD.getIdProjectExclusive());
    }
    System.out.println("pidList" + pidList);
    return pidList;
  }

  public List<ProjectRoleActor> getProjectByUser(String user) {
    List<Double> pidList = pidList(projectExclusiveTETDService.getAll());
    List<String> groupNameList = membershipbaseService.groupNameList(membershipbaseService.getGroupName(user));
    groupNameList.add(user);
    return projectRoleActorRepository.findByProjectIdNotInAndRoleTypeParameterIn(pidList, groupNameList);

  }

  List<Double> projectIdList(List<ProjectRoleActor> liste) {
    List<Double> projectIdList = new ArrayList<>();
    for (ProjectRoleActor projectRoleActor : liste) {
      projectIdList.add(projectRoleActor.getProjectId());
    }
    return projectIdList;
  }

}
