package com.exam.services;

import java.util.Set;

import com.exam.dto.CategoryDto;
import com.exam.entities.Category;
import com.exam.exceptions.CategoryNotFoundException;

public interface CategoryService {
	public Category addCategory(CategoryDto categoryDto);

	public Category updateCategory(Long id,CategoryDto categoryDto) throws CategoryNotFoundException;

	public Set<Category> getCategories() throws CategoryNotFoundException;

	public Category getCategory(Long id) throws CategoryNotFoundException;

	public void deleteCategory(Long id) throws CategoryNotFoundException;
}
