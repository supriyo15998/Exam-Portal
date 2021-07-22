package com.exam.exceptions.validations;

import java.util.List;

import org.springframework.validation.FieldError;

public class ValidateCategoryException extends Exception {
	private List<FieldError> errors;

	public ValidateCategoryException() {
		super();
	}

	public ValidateCategoryException(String message) {
		super(message);
	}

	public ValidateCategoryException(List<FieldError> errors) {
		super();
		this.errors = errors;
	}

	public List<FieldError> getErrors() {
		return errors;
	}
}
