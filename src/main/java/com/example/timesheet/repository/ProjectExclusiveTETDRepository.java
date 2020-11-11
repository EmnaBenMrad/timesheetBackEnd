package com.example.timesheet.repository;

import com.example.timesheet.model.ProjectExclusiveTETD;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectExclusiveTETDRepository extends MongoRepository<ProjectExclusiveTETD, String> {
    List<ProjectExclusiveTETD> findAll();

    ProjectExclusiveTETD findTopByOrderByIdDesc();

    void deleteProjectExclusiveTETDById(String id);

}
