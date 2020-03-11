package com.example.timesheet.repository;


import com.example.timesheet.model.InvAppliUserRole;
import com.example.timesheet.model.Project;
import com.example.timesheet.model.Projectrole;
import com.example.timesheet.model.Userbase;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface InvAppliUserRoleRepository extends MongoRepository<InvAppliUserRole, String> {

  @Query("{'project.idProject' : ?0 , 'userbase.idUser' : ?1}")
  InvAppliUserRole findInvAppliUserRoleByProjectIdtAndUserbaseId(Double projectId, Double userbaseId);

}
