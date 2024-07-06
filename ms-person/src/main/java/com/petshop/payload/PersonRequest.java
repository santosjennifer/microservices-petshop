package com.petshop.payload;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;

public class PersonRequest {
	
    @NotBlank(message = "O nome deve ser informado")
	private String name;
    
    @CPF(message = "O cpf deve ser válido")
    @NotBlank(message = "O cpf deve ser informado")
	private String cpf;
    
	private String phone;

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
	
}
