package com.exam.services.implementations;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.dto.QuestionDto;
import com.exam.entities.Question;
import com.exam.entities.Quiz;
import com.exam.exceptions.QuestionNotFoundException;
import com.exam.exceptions.QuizNotFoundException;
import com.exam.helpers.Helper;
import com.exam.repositories.QuestionRepository;
import com.exam.repositories.QuizRepository;
import com.exam.services.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private QuizRepository quizRepository;

	@Override
	public Question addQuestion(QuestionDto questionDto) throws QuizNotFoundException {
		Quiz quiz = this.quizRepository.findById(questionDto.getQuizId())
				.orElseThrow(() -> new QuizNotFoundException(Helper.NO_QUIZ_FOUND));
		Question question = new Question();
		question.setContent(questionDto.getContent());
		if (questionDto.getImage() != null)
			question.setImage(questionDto.getImage());
		question.setOption1(questionDto.getOption1());
		question.setOption2(questionDto.getOption2());
		question.setOption3(questionDto.getOption3());
		question.setOption4(questionDto.getOption4());
		question.setAnswer(questionDto.getAnswer());
		question.setQuiz(quiz);
		return this.questionRepository.save(question);
	}

	@Override
	public Question updateQuestion(Long id, QuestionDto questionDto)
			throws QuizNotFoundException, QuestionNotFoundException {
		Question question = this.questionRepository.findById(id)
				.orElseThrow(() -> new QuestionNotFoundException(Helper.NO_QUESTION_FOUND));
		Quiz quiz = this.quizRepository.findById(questionDto.getQuizId())
				.orElseThrow(() -> new QuizNotFoundException(Helper.NO_QUIZ_FOUND));
		question.setContent(questionDto.getContent());
		if (questionDto.getImage() != null)
			question.setImage(questionDto.getImage());
		question.setOption1(questionDto.getOption1());
		question.setOption2(questionDto.getOption2());
		question.setOption3(questionDto.getOption3());
		question.setOption4(questionDto.getOption4());
		question.setAnswer(questionDto.getAnswer());
		question.setQuiz(quiz);
		return this.questionRepository.save(question);
	}

	@Override
	public Set<Question> getQuestionsOfQuiz(Long quizId) throws QuizNotFoundException, QuestionNotFoundException {
		Quiz quiz = this.quizRepository.findById(quizId)
				.orElseThrow(() -> new QuizNotFoundException(Helper.NO_QUIZ_FOUND));
		Set<Question> questions = quiz.getQuestions();
		if (questions.isEmpty())
			throw new QuestionNotFoundException(Helper.NO_QUESTION_FOUND);
		return new HashSet<>(questions);

	}

	@Override
	public Question getQuestion(Long questionId) throws QuestionNotFoundException {
		return this.questionRepository.findById(questionId)
				.orElseThrow(() -> new QuestionNotFoundException(Helper.NO_QUESTION_FOUND));
	}

	@Override
	public void deleteQuestion(Long questionId) throws QuestionNotFoundException {
		Question question = this.questionRepository.findById(questionId)
				.orElseThrow(() -> new QuestionNotFoundException(Helper.NO_QUESTION_FOUND));
		this.questionRepository.deleteById(question.getQuestionId());

	}

}
