package com.petshop.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.petshop.exception.PersonCannotDeletedException;
import com.petshop.exception.PersonNotFoundException;
import com.petshop.exception.ResponseBody;

import feign.FeignException;
import feign.RetryableException;

@ControllerAdvice
public class PersonExceptionsHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseBody> handleValidationExceptions(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String errorMessage = fieldError.getDefaultMessage();

        ResponseBody response = new ResponseBody(errorMessage);
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
	@ExceptionHandler(PersonNotFoundException.class)
	public ResponseEntity<ResponseBody> handlePersonNotFoundException(PersonNotFoundException ex) {
		ResponseBody response = new ResponseBody(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PersonCannotDeletedException.class)
	public ResponseEntity<ResponseBody> handlePersonCannotDeleted(PersonCannotDeletedException ex) {
		ResponseBody response = new ResponseBody(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
	}
    
	@ExceptionHandler({ FeignException.ServiceUnavailable.class })
	public ResponseEntity<ResponseBody> handleFeignServiceUnavailable() {
		ResponseBody response = new ResponseBody("Serviço de animais indisponível. Tente mais tarde.");
		return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
	}
	
    @ExceptionHandler({ RetryableException.class })
    public ResponseEntity<ResponseBody> handleRetryableException() {
        ResponseBody response = new ResponseBody("Falha temporária na conexão com o serviço de animais. Tente mais tarde.");
        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }
    
}
