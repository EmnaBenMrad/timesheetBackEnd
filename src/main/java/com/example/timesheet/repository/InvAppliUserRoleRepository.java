package com.example.timesheet.repository;


import com.example.timesheet.model.InvAppliUserRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;


public interface InvAppliUserRoleRepository extends MongoRepository<InvAppliUserRole, String> {

    @Query("{'project.idProject' : ?0 , 'userbase.idUser' : ?1}")
    InvAppliUserRole findInvAppliUserRoleByProject_IdProjectAndUserbase_IdUser(Double projectId, Double userbaseId);

    @Query("{ 'userbase.idUser' : ?0}")
        //List<Project> findProjectsBy
    List<InvAppliUserRole> findInvAppliUserRolesByUserbase_IdUser(Double idUser);
}
