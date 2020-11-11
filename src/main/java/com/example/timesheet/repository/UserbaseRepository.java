package com.example.timesheet.repository;

import com.example.timesheet.model.Userbase;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserbaseRepository extends MongoRepository<Userbase, String> {

    List<Userbase> findAll();

    @Query("{'idUser': ?0 }")
    Userbase findByIdUser(Double idUser);

    /* @Query("{'username': ?0 }")*/
    Userbase findUserbaseByUsername(String userbame);

    Optional<Userbase> findByUsername(String username);

    Boolean existsByUsername(String username);


}
