package com.example.timesheet.repository;


import com.example.timesheet.model.Projectrole;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRoleRepository extends MongoRepository<Projectrole, String> {
  //List<Projectrole> findAll();
  Projectrole findTopByOrderByIdDesc();

  @Query("{'id_role': ?0 }")
  Projectrole findTopByOrderById_roleDesc();

  @Query("{'id_role': ?0 }")
  Optional<Projectrole> findById_role(Double id_role);

  @Query("{'id_role': ?0 }")
  Projectrole findById_role2(Double id_role);

  @Query("{'id_role': ?0 }")
  Optional<Projectrole> deleteById_role(Double id_role);

}
