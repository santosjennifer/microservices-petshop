package com.petshop.dto;

import java.util.List;

public class PersonDto {

	private String id;
	private String firstName;
	private String lastName;
	private List<AnimalDto> animals;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<AnimalDto> getAnimals() {
		return animals;
	}
	public void setAnimals(List<AnimalDto> animals) {
		this.animals = animals;
	}
	
}
