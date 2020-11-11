package com.example.timesheet.repository;
import com.example.timesheet.model.Imputation;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ImputationRepository extends MongoRepository<Imputation, String>, ImputationRepositoryCustom {

  List<Imputation> findAll();

  Imputation findTopByOrderByIdDesc();

  @Query("{'idImputation': ?0 }")
  Imputation findByIdImputation(int idImputation);

  @Query("{ 'user.idUser' : ?0}")
  List<Imputation> findImputationsByUser_IdUser(Double idUser);

  @DeleteQuery
  void deleteByIdImputation(int idImputation);

  List<Imputation> findByStartDateIsStartingWith(String startDate);

  List<Imputation> findByUser_UsernameContaining(String login);

  List<Imputation> findByStartDateIn(List<String> listeJours);

  List<Imputation> findImputationsByStartDateInAndProject_PnameOrStartDateInAndUser_UsernameOrStartDateInAndProject_PnameAndUser_Username(List<String> listeJours, String project, List<String> listeJours2, String username, List<String> listeJours3, String project2, String username2);


}
