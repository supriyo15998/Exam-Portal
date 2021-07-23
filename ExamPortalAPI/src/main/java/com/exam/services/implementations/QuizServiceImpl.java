package com.exam.services.implementations;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.dto.QuizDto;
import com.exam.entities.Category;
import com.exam.entities.Quiz;
import com.exam.exceptions.CategoryNotFoundException;
import com.exam.exceptions.QuizNotFoundException;
import com.exam.helpers.Helper;
import com.exam.repositories.CategoryRepository;
import com.exam.repositories.QuizRepository;
import com.exam.services.QuizService;

@Service
public class QuizServiceImpl implements QuizService {
	@Autowired
	private QuizRepository quizRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Quiz addQuiz(QuizDto quizDto) throws CategoryNotFoundException {
		Category category = this.categoryRepository.findById(quizDto.getCategoryId())
				.orElseThrow(() -> new CategoryNotFoundException(Helper.NO_CATEGORY_FOUND));
		Quiz quiz = new Quiz();
		quiz.setTitle(quizDto.getTitle());
		quiz.setDescription(quizDto.getDescription());
		quiz.setMaxMarks(String.valueOf(quizDto.getMaxMarks()));
		quiz.setNumberOfQuestions(String.valueOf(quizDto.getNumberOfQuestions()));
		quiz.setCategory(category);

		return this.quizRepository.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Long id, QuizDto quizDto) throws CategoryNotFoundException, QuizNotFoundException {
		Quiz quiz = this.quizRepository.findById(id).orElseThrow(() -> new QuizNotFoundException(Helper.NO_QUIZ_FOUND));
		Category category = this.categoryRepository.findById(quizDto.getCategoryId())
				.orElseThrow(() -> new CategoryNotFoundException(Helper.NO_CATEGORY_FOUND));
		quiz.setTitle(quizDto.getTitle());
		quiz.setDescription(quizDto.getDescription());
		quiz.setMaxMarks(String.valueOf(quizDto.getMaxMarks()));
		quiz.setNumberOfQuestions(String.valueOf(quizDto.getNumberOfQuestions()));
		quiz.setCategory(category);
		return this.quizRepository.save(quiz);
	}

	@Override
	public Set<Quiz> getAllQuizes() throws QuizNotFoundException {
		Set<Quiz> quizes = new LinkedHashSet<>(this.quizRepository.findAll());
		if (quizes.isEmpty())
			throw new QuizNotFoundException(Helper.NO_QUIZ_FOUND);
		return quizes;
	}

	@Override
	public Quiz getQuiz(Long id) throws QuizNotFoundException {
		return this.quizRepository.findById(id).orElseThrow(() -> new QuizNotFoundException(Helper.NO_QUIZ_FOUND));
	}

	@Override
	public void deleteQuiz(Long id) throws QuizNotFoundException {
		Quiz quiz = this.quizRepository.findById(id).orElseThrow(() -> new QuizNotFoundException(Helper.NO_QUIZ_FOUND));
		this.quizRepository.deleteById(quiz.getQid());

	}

}
