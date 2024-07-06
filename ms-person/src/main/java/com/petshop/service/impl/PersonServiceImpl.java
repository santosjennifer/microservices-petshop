package com.petshop.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petshop.client.AnimalClient;
import com.petshop.dto.AnimalDto;
import com.petshop.dto.PersonDto;
import com.petshop.exception.PersonCannotDeletedException;
import com.petshop.exception.PersonNotFoundException;
import com.petshop.model.Person;
import com.petshop.repository.PersonRepository;
import com.petshop.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonRepository repository;
	
	@Autowired
	private AnimalClient animalClient;

	@Override
	@Transactional
	public PersonDto create(PersonDto personDto) {
		return salvePerson(personDto);
	}

	@Override
	@Transactional
	public PersonDto update(String id, PersonDto personDto) {
		personDto.setId(id);
		return salvePerson(personDto);
	}

	@Override
	@Transactional
	public void remove(String id) {
		Person person = repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
		
		List<AnimalDto> listAnimals = animalClient.findAnimals(person.getId());
		
		if (!listAnimals.isEmpty()) {
			throw new PersonCannotDeletedException();
		}
		
		repository.deleteById(id);
	}

	@Override
	public PersonDto findPersonById(String id) {
	    PersonDto dto = repository.findById(id)
	                                     .map(this::convertEntityToDto)
	                                     .orElseThrow(() -> new PersonNotFoundException(id));
	    
	    List<AnimalDto> animals = animalClient.findAnimals(id);
	    dto.setAnimals(animals);
	    
	    return dto;
	}

	@Override
	public List<PersonDto> findAll() {
		List<Person> persons = repository.findAll();

		return persons.stream()
				.map(person -> convertEntityToDto(person))
				.collect(Collectors.toList());
	}
	
	private Person convertDtoToEntity(PersonDto dto) {
		Person entity = new Person();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setPhone(dto.getPhone());
		return entity;
	}
	
	private PersonDto convertEntityToDto(Person entity) {
		PersonDto dto = new PersonDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCpf(entity.getCpf());
		dto.setPhone(entity.getPhone());
		return dto;
	}
	
	private PersonDto salvePerson(PersonDto personDto) {
		Person person = convertDtoToEntity(personDto);
		person = repository.save(person);
		return convertEntityToDto(person);
	}

}
