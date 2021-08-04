package com.exam.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.dto.QuestionDto;
import com.exam.entities.Question;
import com.exam.entities.Quiz;
import com.exam.exceptions.QuestionNotFoundException;
import com.exam.exceptions.QuizNotFoundException;
import com.exam.exceptions.validations.ValidateQuestionException;
import com.exam.helpers.Helper;
import com.exam.helpers.SuccessMessage;
import com.exam.services.QuestionService;
import com.exam.services.QuizService;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuizService quizService;

	@PostMapping("/create")
	public SuccessMessage createQuestion(@Valid @RequestBody QuestionDto questionDto, BindingResult br)
			throws ValidateQuestionException, QuizNotFoundException {
		if (br.hasErrors())
			throw new ValidateQuestionException(br.getFieldErrors());
		Question question = this.questionService.addQuestion(questionDto);
		return new SuccessMessage("Question id: " + question.getQuestionId() + " added successfully !");
	}

	@PutMapping("/update/{questionId}")
	public SuccessMessage updateQuestion(@Valid @RequestBody QuestionDto questionDto,
			@PathVariable("questionId") String questionId, BindingResult br)
			throws ValidateQuestionException, NumberFormatException, QuizNotFoundException, QuestionNotFoundException {
		if (br.hasErrors())
			throw new ValidateQuestionException(br.getFieldErrors());
		Question question = this.questionService.updateQuestion(Long.parseLong(questionId), questionDto);
		return new SuccessMessage("Question id: " + question.getQuestionId() + " updated successfully !");
	}

	@GetMapping("/get/quiz/{quizId}")
	public Set<Question> viewQuestionsOfQuiz(@PathVariable("quizId") String quizId)
			throws NumberFormatException, QuizNotFoundException, QuestionNotFoundException {
		Quiz quiz = this.quizService.getQuiz(Long.parseLong(quizId));
		Set<Question> questions = quiz.getQuestions();
		if(questions.isEmpty())
			throw new QuestionNotFoundException(Helper.NO_QUESTION_FOUND);
		List<Question> questionList = new ArrayList<>(questions);
		if (questionList.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
			questionList = questionList.subList(0, Integer.parseInt(quiz.getNumberOfQuestions() + 1));
		}
		Collections.shuffle(questionList);
		return new HashSet<>(questionList);
	}
	
	@GetMapping("/get/quiz/all/{quizId}")
	public Set<Question> viewQuestionsOfQuizAdmin(@PathVariable("quizId") String quizId)
			throws NumberFormatException, QuizNotFoundException, QuestionNotFoundException {
		Quiz quiz = this.quizService.getQuiz(Long.parseLong(quizId));
		Set<Question> questions = quiz.getQuestions();
		if(questions.isEmpty())
			throw new QuestionNotFoundException(Helper.NO_QUESTION_FOUND);
//		List<Question> questionList = new ArrayList<>(questions);
//		if (questionList.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
//			questionList = questionList.subList(0, Integer.parseInt(quiz.getNumberOfQuestions() + 1));
//		}
//		Collections.shuffle(questionList);
		return new HashSet<>(questions);
	}

	@GetMapping("/get/question/{questionId}")
	public Question viewQuestion(@PathVariable("questionId") String questionId)
			throws NumberFormatException, QuestionNotFoundException {
		return this.questionService.getQuestion(Long.parseLong(questionId));
	}

	@DeleteMapping("/delete/{questionId}")
	public SuccessMessage deleteQuestion(@PathVariable("questionId") String questionId)
			throws NumberFormatException, QuestionNotFoundException {
		this.questionService.deleteQuestion(Long.parseLong(questionId));
		return new SuccessMessage("Question id: " + questionId + " deleted successfully !");
	}

}
