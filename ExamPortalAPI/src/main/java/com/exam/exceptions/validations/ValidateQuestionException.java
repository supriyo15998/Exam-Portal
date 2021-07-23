package com.exam.exceptions.validations;

import java.util.List;

import org.springframework.validation.FieldError;

public class ValidateQuestionException extends Exception {
	private List<FieldError> errors;

	public ValidateQuestionException() {
		super();
	}

	public ValidateQuestionException(String message) {
		super(message);
	}

	public ValidateQuestionException(List<FieldError> errors) {
		super();
		this.errors = errors;
	}

	public List<FieldError> getErrors() {
		return errors;
	}
}
