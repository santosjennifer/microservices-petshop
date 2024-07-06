package com.petshop.dto;

import java.util.List;

public class PersonDto {

	private String id;
	private String name;
	private String cpf;
	private String phone;
	private List<AnimalDto> animals;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<AnimalDto> getAnimals() {
		return animals;
	}
	public void setAnimals(List<AnimalDto> animals) {
		this.animals = animals;
	}
	
}
