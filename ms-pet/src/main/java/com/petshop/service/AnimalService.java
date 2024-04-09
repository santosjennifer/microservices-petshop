package com.petshop.service;

import java.util.List;
import java.util.Optional;

import com.petshop.dto.AnimalDto;

public interface AnimalService {
	
	List<AnimalDto> findAll();
	Optional<AnimalDto> findById(String id);
	List<AnimalDto> findByGuardian(String id);
	AnimalDto create(AnimalDto animalDto);
	AnimalDto update(String id, AnimalDto animalDto);
	void delete(String id);
	boolean setDead(String id);

}
