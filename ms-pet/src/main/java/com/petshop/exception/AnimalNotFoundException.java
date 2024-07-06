package com.petshop.exception;

public class AnimalNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 364191388323646055L;
	
	public AnimalNotFoundException(String id) {
		super("Animal não encontrado para o ID " + id);
	}

}
