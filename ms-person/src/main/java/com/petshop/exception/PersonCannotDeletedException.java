package com.petshop.exception;

public class PersonCannotDeletedException extends RuntimeException {
	private static final long serialVersionUID = 4551448122740968325L;

	public PersonCannotDeletedException() {
		super("A pessoa possui vínculo com animais e não pode ser excluída");
	}
}
