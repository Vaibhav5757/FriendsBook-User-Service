package com.friendsbook.user.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	// Following is to handle exceptions for hibernate validator
	// For more info, you can refer this article - https://www.baeldung.com/spring-boot-bean-validation
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    StringBuilder sb = new StringBuilder();
	    errors.forEach((key, value) -> {
	    	sb.append(key + " " + value + ". ");
	    });

	    return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
	}

	// Exception Handling for exception occuring due to wrong api calls or to send api responses when
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<String> handleEmailAlreadyInUse(ApiException ex){
		return new ResponseEntity<String>(ex.getMsg(), HttpStatus.BAD_REQUEST);
	}
}
