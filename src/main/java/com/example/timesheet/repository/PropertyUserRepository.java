package com.example.timesheet.repository;

import com.example.timesheet.model.PropertyUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyUserRepository extends MongoRepository<PropertyUser, String> {


    List<PropertyUser> findByPropertyvalueNotNull();


    List<PropertyUser> findByPropertyvalueNotContaining(String propertyvalue);


    List<PropertyUser> findByPropertyvalueContaining(String propertyvalue);
}
