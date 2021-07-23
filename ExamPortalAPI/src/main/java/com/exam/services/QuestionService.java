package com.exam.services;

import java.util.Set;

import com.exam.dto.QuestionDto;
import com.exam.entities.Question;
import com.exam.exceptions.QuestionNotFoundException;
import com.exam.exceptions.QuizNotFoundException;

public interface QuestionService {
	public Question addQuestion(QuestionDto questionDto) throws QuizNotFoundException;

	public Question updateQuestion(Long id, QuestionDto questionDto)
			throws QuizNotFoundException, QuestionNotFoundException;

	public Set<Question> getQuestionsOfQuiz(Long quizId) throws QuizNotFoundException, QuestionNotFoundException;

	public Question getQuestion(Long questionId) throws QuestionNotFoundException;

	public void deleteQuestion(Long questionId) throws QuestionNotFoundException;
}
