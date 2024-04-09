package com.petshop.payload;

import java.util.List;

import com.petshop.dto.AnimalDto;

public class PersonResponseDetails extends PersonResponse {

	List<AnimalDto> animals;

	public List<AnimalDto> getAnimals() {
		return animals;
	}

	public void setAnimals(List<AnimalDto> animals) {
		this.animals = animals;
	}
	
}
