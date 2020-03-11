package com.example.timesheet.repository;


import com.example.timesheet.model.Imputation;
import com.example.timesheet.model.Projectrole;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImputationRepository extends MongoRepository<Imputation, String>, QueryByExampleExecutor<Imputation> {
  List<Imputation> findAll();

  Imputation findTopByOrderByIdDesc();

  @Query("{'idImputation': ?0 }")
  Imputation findByIdImputation(int idImputation);

}
