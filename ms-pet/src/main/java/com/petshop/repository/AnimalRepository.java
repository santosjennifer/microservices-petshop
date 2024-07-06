package com.petshop.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.petshop.model.Animal;

@Repository
public interface AnimalRepository extends MongoRepository<Animal, String> {

	List<Animal> findByGuardian(String id);

}
