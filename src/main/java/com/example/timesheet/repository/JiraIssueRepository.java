package com.example.timesheet.repository;

import com.example.timesheet.model.Jiraissue;

import com.example.timesheet.model.Projectrole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JiraIssueRepository extends MongoRepository<Jiraissue, String> {
  List<Jiraissue> findAll();

  @Query("{'idIssue': ?0 }")
  Jiraissue findByIdIssue(Double idIssue);
}
