package com.exam.exceptions.validations;

import java.util.List;

import org.springframework.validation.FieldError;

public class ValidateQuizException extends Exception {
	private List<FieldError> errors;

	public ValidateQuizException() {
		super();
	}

	public ValidateQuizException(String message) {
		super(message);
	}

	public ValidateQuizException(List<FieldError> errors) {
		super();
		this.errors = errors;
	}

	public List<FieldError> getErrors() {
		return errors;
	}
}
