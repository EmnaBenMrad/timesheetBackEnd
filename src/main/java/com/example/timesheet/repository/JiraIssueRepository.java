package com.example.timesheet.repository;

import com.example.timesheet.model.Jiraissue;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface JiraIssueRepository extends MongoRepository<Jiraissue, String> {
    List<Jiraissue> findAll();

    @Query("{'idIssue': ?0 }")
    Jiraissue findByIdIssue(Double idIssue);

    @Query("{'assignee': null ,'project': { $in: ?0 }, 'issuestatus': {$ne : ?1}}")
    List<Jiraissue> findDistinctByAssigneeNullAndProjectInAndIssuestatusNotLike(List<Double> project, String idIssue);

    @Query("{'assignee': ?0 ,'project': { $in: ?1 }, 'issuestatus': {$ne : ?2}}")
    List<Jiraissue> findDistinctByAssigneeAndProjectInAndIssuestatusNotLike(String assignee, List<Double> project, String idIssue);

    @Query("{'assignee': null ,'project':  ?0 , 'issuestatus': {$ne : ?1}}")
    List<Jiraissue> findDistinctByAssigneeNullAndProjectAndIssuestatusNotLike(Double project, String idIssue);

    @Query("{'assignee': ?0 ,'project':  ?1 , 'issuestatus': {$ne : ?2}}")
    List<Jiraissue> findDistinctByAssigneeAndProjectAndIssuestatusNotLike(String assignee, Double project, String idIssue);
}
