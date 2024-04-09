package com.petshop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.petshop.model.Person;

@Repository
public interface PersonRepository extends MongoRepository<Person, String>{

}
