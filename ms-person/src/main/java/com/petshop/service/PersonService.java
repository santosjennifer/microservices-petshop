package com.petshop.service;

import java.util.List;
import java.util.Optional;

import com.petshop.dto.PersonDto;

public interface PersonService {
	
	PersonDto create(PersonDto personDto);
	PersonDto update(String id, PersonDto personDto);
	void remove(String id);
	Optional<PersonDto> findPersonById(String id);
	List<PersonDto> findAll();
	
}
