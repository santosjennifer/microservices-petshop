package com.petshop.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petshop.client.AnimalClient;
import com.petshop.dto.AnimalDto;
import com.petshop.dto.PersonDto;
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
	public PersonDto create(PersonDto personDto) {
		return salvePerson(personDto);
	}

	@Override
	public PersonDto update(String id, PersonDto personDto) {
		personDto.setId(id);
		return salvePerson(personDto);
	}

	@Override
	public void remove(String id) {
		repository.deleteById(id);
		
	}

	@Override
	public Optional<PersonDto> findPersonById(String id) {
		Optional<Person> person = repository.findById(id);
		
		if(person.isPresent()) {
			PersonDto dto = convertEntityToDto(person.get());
			
			List<AnimalDto> animals = animalClient.findAnimals(id);
			dto.setAnimals(animals);
			
			return Optional.of(dto);
		}
		
		return Optional.empty();
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
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		return entity;
	}
	
	private PersonDto convertEntityToDto(Person entity) {
		PersonDto dto = new PersonDto();
		dto.setId(entity.getId());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		return dto;
	}
	
	private PersonDto salvePerson(PersonDto personDto) {
		Person person = convertDtoToEntity(personDto);
		person = repository.save(person);
		
		return convertEntityToDto(person);
	}

}
