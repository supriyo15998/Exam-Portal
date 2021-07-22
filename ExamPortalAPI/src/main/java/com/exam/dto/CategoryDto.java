package com.exam.dto;

import javax.validation.constraints.NotBlank;

public class CategoryDto {
	private Long cid;
	@NotBlank(message = "Category title can not be blank")
	private String title;
	@NotBlank(message = "Category description can not be blank")
	private String description;
	
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
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

	
}
