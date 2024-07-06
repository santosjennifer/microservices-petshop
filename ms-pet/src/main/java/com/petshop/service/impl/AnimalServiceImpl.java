package com.petshop.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petshop.client.PersonClient;
import com.petshop.dto.AnimalDto;
import com.petshop.dto.PersonDto;
import com.petshop.exception.AnimalNotFoundException;
import com.petshop.model.Animal;
import com.petshop.repository.AnimalRepository;
import com.petshop.service.AnimalService;

@Service
public class AnimalServiceImpl implements AnimalService {

	@Autowired
	private AnimalRepository repository;

	@Autowired
	private PersonClient personClient;

	@Override
	public List<AnimalDto> findAll() {
		List<Animal> animals = repository.findAll();

	    return animals.stream()
	            .map(animal -> {
	                AnimalDto animalDto = convertToDto(animal);
	                if (animalDto.getGuardian() != null) {
	                    PersonDto person = personClient.findPersonById(animalDto.getGuardian());
	                    animalDto.setGuardian(person.getName());
	                }
	                return animalDto;
	            })
	            .collect(Collectors.toList());
	}

	@Override
	public AnimalDto findById(String id) {
		AnimalDto animalDto = repository.findById(id)
					.map(this::convertToDto)
					.orElseThrow(() -> new AnimalNotFoundException(id));

		if (animalDto.getGuardian() != null) {
			PersonDto person = personClient.findPersonById(animalDto.getGuardian());
			animalDto.setGuardian(person.getName());
		}

		return animalDto;
	}

	@Override
	public List<AnimalDto> findByGuardian(String id) {
		List<Animal> animals = repository.findByGuardian(id);

		return animals.stream().map(animal -> convertToDto(animal)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public AnimalDto create(AnimalDto animalDto) {
		if (animalDto.getGuardian() != null) {
			personClient.findPersonById(animalDto.getGuardian());
		}

		if (animalDto.getId() == null) {
			animalDto.setAlive(true);
		}

		Animal animal = convertToEntity(animalDto);
		animal = repository.save(animal);

		return convertToDto(animal);
	}

	@Override
	public AnimalDto update(String id, AnimalDto animalDto) {
		AnimalDto dto = findById(id);

		animalDto.setId(id);
		animalDto.setAlive(dto.isAlive());

		return create(animalDto);
	}

	@Override
	public void delete(String id) {
		repository.deleteById(id);
	}

	@Override
	public boolean setDead(String id) {
		Optional<Animal> animal = repository.findById(id);

		if (animal.isPresent()) {
			animal.get().setAlive(false);
			repository.save(animal.get());

			return true;
		}

		return false;
	}

	private AnimalDto convertToDto(Animal animal) {
		AnimalDto dto = new AnimalDto();
		dto.setId(animal.getId());
		dto.setName(animal.getName());
		dto.setAge(animal.getAge());
		dto.setBreed(animal.getBreed());
		dto.setAlive(animal.isAlive());
		dto.setGuardian(animal.getGuardian());

		return dto;
	}

	private Animal convertToEntity(AnimalDto dto) {
		Animal entity = new Animal();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setAge(dto.getAge());
		entity.setBreed(dto.getBreed());
		entity.setAlive(dto.isAlive());
		entity.setGuardian(dto.getGuardian());

		return entity;
	}

}
