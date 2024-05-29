package com.library.LibraryManagement.exceptions;

import java.util.Date;

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
		String description = "Adding Book with same ISBN is not allowed";
		ErrorMessage error = new ErrorMessage(statusCode,new Date(), errorMessage, description);
		return new ResponseEntity<ErrorMessage>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {NoResourceFoundException.class})
	public ResponseEntity<ErrorMessage> handleNoResourceFoundException(NoResourceFoundException ex,WebRequest request) {
		String errorMessage = ex.getMessage();
		int statusCode = HttpStatus.NO_CONTENT.value();
		String description = "No Resource Exists";
		ErrorMessage error = new ErrorMessage(statusCode,new Date(), errorMessage, description);
		return new ResponseEntity<ErrorMessage>(error,HttpStatus.NO_CONTENT);
	}

}
