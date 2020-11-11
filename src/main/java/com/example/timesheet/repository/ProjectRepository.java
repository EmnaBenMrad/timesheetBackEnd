package com.example.timesheet.repository;

import com.example.timesheet.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {
    List<Project> findAll();

    @Query("{'idProject': ?0 }")
    Project findByIdProject(Double idProject);

    @Query("{'idProject': { $in: ?0 } }")
    List<Project> findByIdProjectIn(List<Double> pidProjectRoleActor);
}
