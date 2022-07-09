package com.mt.insurancepolicies.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;



@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ControllerException.class)
	public ResponseEntity<Error> exceptionHandleMethod(ControllerException e, WebRequest request) {
		Error info = new Error(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<Error>(info, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> validationExceptionMethod(MethodArgumentNotValidException e) {
		Error info = new Error(new Date(), "Validation Error",
				e.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<Error>(info, HttpStatus.BAD_REQUEST);
	}
}
