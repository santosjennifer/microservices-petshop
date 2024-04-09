package com.petshop.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class PersonRequest {
	
    @NotBlank(message = "O nome deve ser informado")
    @NotEmpty(message = "O nome deve ser informado")
	private String firstName;
	
    @NotBlank(message = "O sobrenome deve ser informado")
    @NotEmpty(message = "O sobrenome deve ser informado")
	private String lastName;

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

}
