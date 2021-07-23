package com.exam.services;

import java.util.Set;

import com.exam.dto.QuizDto;
import com.exam.entities.Quiz;
import com.exam.exceptions.CategoryNotFoundException;
import com.exam.exceptions.QuizNotFoundException;

public interface QuizService {
	public Quiz addQuiz(QuizDto quizDto) throws CategoryNotFoundException;

	public Quiz updateQuiz(Long id, QuizDto quizDto) throws CategoryNotFoundException,QuizNotFoundException;

	public Set<Quiz> getAllQuizes() throws QuizNotFoundException;

	public Quiz getQuiz(Long id) throws QuizNotFoundException;

	public void deleteQuiz(Long id) throws QuizNotFoundException;

}
