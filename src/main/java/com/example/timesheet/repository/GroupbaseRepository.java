package com.example.timesheet.repository;

import com.example.timesheet.model.Groupbase;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GroupbaseRepository extends MongoRepository<Groupbase, String> {

    Groupbase findTopByOrderByIdDesc();

    List<Groupbase> findAll();

    Groupbase findGroupbaseById(String id);

    void deleteGroupbaseById(String id);

}
