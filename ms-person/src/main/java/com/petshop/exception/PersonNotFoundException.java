package com.petshop.exception;

public class PersonNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -4905933259792649239L;
	
	public PersonNotFoundException(String id) {
		super("Pessoa não encontrada para o ID " + id);
	}

}
