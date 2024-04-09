package com.petshop.payload;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AnimalRequest {

	@NotBlank(message = "O nome deve ser informado")
	@Size(max = 100, message = "O nome não pode exceder 100 caracteres")
	private String name;
	
	@NotNull(message = "A idade deve ser informada")
    @Min(value = 0, message = "A idade deve ser um número positivo")
	private Integer age;
	
	private String breed;
	private String guardian;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public String getGuardian() {
		return guardian;
	}
	public void setGuardian(String guardian) {
		this.guardian = guardian;
	}
	
}
