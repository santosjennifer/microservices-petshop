package com.petshop.service;

import java.util.List;

import com.petshop.dto.AnimalDto;

public interface AnimalService {
	
	List<AnimalDto> findAll();
	AnimalDto findById(String id);
	List<AnimalDto> findByGuardian(String id);
	AnimalDto create(AnimalDto animalDto);
	AnimalDto update(String id, AnimalDto animalDto);
	void delete(String id);
	boolean setDead(String id);

}
