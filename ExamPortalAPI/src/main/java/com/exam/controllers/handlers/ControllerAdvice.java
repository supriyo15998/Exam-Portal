package com.exam.controllers.handlers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.exam.exceptions.UserAlreadyExistsException;
import com.exam.exceptions.UserNotFoundException;
import com.exam.helpers.ErrorMessage;

@RestControllerAdvice
public class ControllerAdvice {
	@ExceptionHandler(UserAlreadyExistsException.class)
	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	public ErrorMessage handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
		List<String> errors = new ArrayList<>();
		errors.add(ex.getMessage());
		return new ErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY.toString(), errors);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleUserNotFoundException(UserNotFoundException ex) {
		List<String> errors = new ArrayList<>();
		errors.add(ex.getMessage());
		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), errors);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
		List<String> errors = new ArrayList<>();
		errors.add(ex.getMessage());
		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), errors);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
		List<String> errors = new ArrayList<>();
		errors.add(ex.getMessage());
		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), errors);
	}
	
}
