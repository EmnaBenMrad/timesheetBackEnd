package com.example.timesheet.repository;


import com.example.timesheet.model.Projectrole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProjectRoleRepository extends MongoRepository<Projectrole, String> {

    Projectrole findTopByOrderByIdDesc();

    Projectrole findByIdRole(Double id);

    @Query("{'id_role': ?0 }")
    Projectrole findTopByOrderById_roleDesc();

    @Query("{'id_role': ?0 }")
    Optional<Projectrole> findById_role(Double id_role);


}
