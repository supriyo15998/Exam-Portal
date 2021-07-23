package com.exam.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class QuizDto {

	private Long qid;
	@NotBlank(message = "Title can not be blank")
	private String title;
	@NotBlank(message = "Description can not be blank")
	private String description;
	@NotNull(message = "Maximum marks can not be null")
	@Min(value = 1, message = "Maximum marks should be at least 1")
	private Integer maxMarks;
	@NotNull(message = "Number of questions can not be null")
	@Min(value = 1, message = "Number of questions should be at least 1")
	private Integer numberOfQuestions;
	@NotNull(message = "Category id can not be null")
	private Long categoryId;

	public Long getQid() {
		return qid;
	}

	public void setQid(Long qid) {
		this.qid = qid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(Integer maxMarks) {
		this.maxMarks = maxMarks;
	}

	public Integer getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setNumberOfQuestions(Integer numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

}
