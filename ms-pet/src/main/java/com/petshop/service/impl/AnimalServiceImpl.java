package com.petshop.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petshop.dto.AnimalDto;
import com.petshop.model.Animal;
import com.petshop.repository.AnimalRepository;
import com.petshop.service.AnimalService;

@Service
public class AnimalServiceImpl implements AnimalService {
	
	@Autowired
	private AnimalRepository repository;

	@Override
	public List<AnimalDto> findAll() {
		List<Animal> animals = repository.findAll();
		
		return animals.stream()
				.map(animal -> convertToDto(animal))
				.collect(Collectors.toList());
	}

	@Override
	public Optional<AnimalDto> findById(String id) {
		Optional<Animal> animal = repository.findById(id);
		
		if (animal.isPresent()) {
			return Optional.of(convertToDto(animal.get()));
		}
		
		return Optional.empty();
	}

	@Override
	public List<AnimalDto> findByGuardian(String id) {
		List<Animal> animals = repository.findByGuardian(id);
		
		return animals.stream()
				.map(animal -> convertToDto(animal))
				.collect(Collectors.toList());
	}

	@Override
	public AnimalDto create(AnimalDto animalDto) {
		Animal animal = convertToEntity(animalDto);
		animal = repository.save(animal);
		
		return convertToDto(animal);
	}

	@Override
	public AnimalDto update(String id, AnimalDto animalDto) {
		animalDto.setId(id);
		
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
		entity.setGuardian(dto.getGuardian());
		
		return entity;
	}

}
