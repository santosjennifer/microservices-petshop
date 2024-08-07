package com.petshop.service;

import java.util.List;

import com.petshop.dto.PersonDto;

public interface PersonService {
	
	PersonDto create(PersonDto personDto);
	PersonDto update(String id, PersonDto personDto);
	void remove(String id);
	PersonDto findPersonById(String id);
	List<PersonDto> findAll();
	
}
