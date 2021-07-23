package com.exam.controllers.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.exam.exceptions.CategoryNotFoundException;
import com.exam.exceptions.QuestionNotFoundException;
import com.exam.exceptions.QuizNotFoundException;
import com.exam.exceptions.TokenExpiredException;
import com.exam.exceptions.UserAlreadyExistsException;
import com.exam.exceptions.UserNotFoundException;
import com.exam.exceptions.validations.ValidateCategoryException;
import com.exam.exceptions.validations.ValidateQuestionException;
import com.exam.exceptions.validations.ValidateQuizException;
import com.exam.helpers.ErrorMessage;

import io.jsonwebtoken.ExpiredJwtException;

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

	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public ErrorMessage handleBadCredentialsException(BadCredentialsException ex) {
		List<String> errors = new ArrayList<>();
		errors.add(ex.getMessage());
		return new ErrorMessage(HttpStatus.UNAUTHORIZED.toString(), errors);
	}

	@ExceptionHandler(ExpiredJwtException.class)
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public ErrorMessage handleExpiredJwtException(ExpiredJwtException ex) {

		List<String> errors = new ArrayList<>();
		errors.add(ex.getMessage());
		return new ErrorMessage(HttpStatus.UNAUTHORIZED.toString(), errors);
	}

	@ExceptionHandler(TokenExpiredException.class)
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public ErrorMessage handleTokenExpiredException(TokenExpiredException ex) {

		List<String> errors = new ArrayList<>();
		errors.add(ex.getMessage());
		return new ErrorMessage(HttpStatus.UNAUTHORIZED.toString(), errors);
	}

	@ExceptionHandler(CategoryNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleCategoryNotFoundException(CategoryNotFoundException ex) {
		List<String> errors = new ArrayList<>();
		errors.add(ex.getMessage());
		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), errors);
	}

	@ExceptionHandler(ValidateCategoryException.class)
	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	public ErrorMessage handleValidateCategoryException(ValidateCategoryException ex) {
		List<String> errors = ex.getErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
		return new ErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY.toString(), errors);
	}

	@ExceptionHandler(ValidateQuizException.class)
	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	public ErrorMessage handleValidateQuizException(ValidateQuizException ex) {
		List<String> errors = ex.getErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
		return new ErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY.toString(), errors);

	}
	
	@ExceptionHandler(ValidateQuestionException.class)
	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	public ErrorMessage handleValidateQuestionException(ValidateQuestionException ex) {
		List<String> errors = ex.getErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
		return new ErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY.toString(), errors);

	}

	@ExceptionHandler(NumberFormatException.class)
	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	public ErrorMessage handleNumberFormatException(NumberFormatException ex) {
		List<String> errors = new ArrayList<>();
		errors.add(ex.getMessage());
		return new ErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY.toString(), errors);
	}

	@ExceptionHandler(QuizNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleQuizNotFoundException(QuizNotFoundException ex) {
		List<String> errors = new ArrayList<>();
		errors.add(ex.getMessage());
		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), errors);
	}

	@ExceptionHandler(QuestionNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleQuestionNotFoundException(QuestionNotFoundException ex) {
		List<String> errors = new ArrayList<>();
		errors.add(ex.getMessage());
		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), errors);
	}

}
