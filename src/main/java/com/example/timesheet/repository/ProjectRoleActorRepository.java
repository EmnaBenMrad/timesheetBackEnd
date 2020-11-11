package com.example.timesheet.repository;

import com.example.timesheet.model.ProjectRoleActor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRoleActorRepository extends MongoRepository<ProjectRoleActor, String> {
    List<ProjectRoleActor> findAll();

    @Query("{'idProjectRoleActor': ?0 }")
    ProjectRoleActor findByIdProject(Double idProject);


    @Query("{'projectId': {$nin: ?0},'roleTypeParameter': { $in: ?1 }}")
    List<ProjectRoleActor> findByProjectIdNotInAndRoleTypeParameterIn(List<Double> pid, List<String> roleTypeParameterList);


}
