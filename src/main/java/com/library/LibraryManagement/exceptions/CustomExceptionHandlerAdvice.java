package com.library.LibraryManagement.exceptions;

import java.util.Date;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.library.LibraryManagement.entity.ErrorMessage;

@ControllerAdvice
public class CustomExceptionHandlerAdvice {
	@ExceptionHandler(value = {ResourceAlreadyExistsException.class})
	public ResponseEntity<ErrorMessage> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex,WebRequest request) {
		String errorMessage = ex.getMessage();
		int statusCode = HttpStatus.BAD_REQUEST.value();
		String description = "Resource Already Exists";
		ErrorMessage error = new ErrorMessage(statusCode,new Date(), errorMessage, description);
		return new ResponseEntity<ErrorMessage>(error,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = {ResourceNotFoundException.class})
	public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException ex,WebRequest request) {
		String errorMessage = ex.getMessage();
		int statusCode = HttpStatus.NOT_FOUND.value();
		String description = "Resource not found";
		ErrorMessage error = new ErrorMessage(statusCode,new Date(), errorMessage, description);
		return new ResponseEntity<ErrorMessage>(error,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = {ConstraintViolationException.class})
	public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ConstraintViolationException ex,WebRequest request) {
		String errorMessage = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList()).toString();
		int statusCode = HttpStatus.BAD_REQUEST.value();
		String description = "Must not to be Blank or Null";
		ErrorMessage error = new ErrorMessage(statusCode,new Date(), errorMessage, description);
		return new ResponseEntity<ErrorMessage>(error,HttpStatus.BAD_REQUEST);
	}



}
