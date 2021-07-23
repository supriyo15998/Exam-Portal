package com.exam.controllers;

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

import com.exam.dto.QuizDto;
import com.exam.entities.Quiz;
import com.exam.exceptions.CategoryNotFoundException;
import com.exam.exceptions.QuizNotFoundException;
import com.exam.exceptions.validations.ValidateQuizException;
import com.exam.helpers.SuccessMessage;
import com.exam.services.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {
	@Autowired
	private QuizService quizService;

	@PostMapping("/create")
	public SuccessMessage addQuiz(@Valid @RequestBody QuizDto quizDto, BindingResult br)
			throws ValidateQuizException, CategoryNotFoundException {
		if (br.hasErrors()) {
			throw new ValidateQuizException(br.getFieldErrors());
		}
		Quiz quiz = this.quizService.addQuiz(quizDto);
		return new SuccessMessage("Quiz " + quiz.getTitle() + " added successfully !");
	}

	@PutMapping("/update/{id}")
	public SuccessMessage updateQuiz(@Valid @RequestBody QuizDto quizDto, @PathVariable("id") String id,
			BindingResult br)
			throws ValidateQuizException, CategoryNotFoundException, QuizNotFoundException, NumberFormatException {
		if (br.hasErrors())
			throw new ValidateQuizException(br.getFieldErrors());
		Quiz updatedQuiz = this.quizService.updateQuiz(Long.parseLong(id), quizDto);
		return new SuccessMessage("Quiz " + updatedQuiz.getQid() + " updated successfully !");
	}

	@GetMapping("/get/all")
	public Set<Quiz> getAllQuizes() throws QuizNotFoundException {
		return this.quizService.getAllQuizes();
	}

	@GetMapping("/get/{id}")
	public Quiz getQuiz(@PathVariable("id") String id) throws NumberFormatException, QuizNotFoundException {
		return this.quizService.getQuiz(Long.parseLong(id));
	}

	@DeleteMapping("/delete/{id}")
	public SuccessMessage deleteQuiz(@PathVariable("id") String id)
			throws NumberFormatException, QuizNotFoundException {
		System.out.println("==========in controller==============");
		this.quizService.deleteQuiz(Long.parseLong(id));
		return new SuccessMessage("Quiz id: " + id + " deleted successfully !");
	}

}
