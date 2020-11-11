package com.example.timesheet.repository;

import com.example.timesheet.model.Membershipbase;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipbaseRepository extends MongoRepository<Membershipbase, String> {

    @Query("{'userName': ?0 }")
    List<Membershipbase> findByUserNameEquals(String user);

    @Query("{'groupName': { $in: ?0 } }")
    List<Membershipbase> findByGroupNameIn(List<String> groupName);


    @Query("{'groupbase.groupname': { $in: ?0 },'groupbase.statut': ?1  }")
    List<Membershipbase> findByGroupbase_GroupnameInAndGroupbase_Statut(List<String> groupName, int statut);

}
