package com.exam.services.implementations;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.dto.CategoryDto;
import com.exam.entities.Category;
import com.exam.exceptions.CategoryNotFoundException;
import com.exam.helpers.Helper;
import com.exam.repositories.CategoryRepository;
import com.exam.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRespository;

	@Override
	public Category addCategory(CategoryDto categoryDto) {
		Category category = new Category();
		category.setTitle(categoryDto.getTitle());
		category.setDescription(categoryDto.getDescription());
		return this.categoryRespository.save(category);
	}

	@Override
	public Category updateCategory(Long id,CategoryDto categoryDto) throws CategoryNotFoundException {
		Category category = this.categoryRespository.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException(Helper.NO_CATEGORY_FOUND));

		category.setTitle(categoryDto.getTitle());
		category.setDescription(categoryDto.getDescription());
		return this.categoryRespository.save(category);
	}

	@Override
	public Set<Category> getCategories() throws CategoryNotFoundException {
		Set<Category> categories = new LinkedHashSet<>(this.categoryRespository.findAll());
		if (categories.isEmpty())
			throw new CategoryNotFoundException(Helper.NO_CATEGORY_FOUND);
		return categories;
	}

	@Override
	public Category getCategory(Long id) throws CategoryNotFoundException {
		return this.categoryRespository.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException(Helper.NO_CATEGORY_FOUND));
	}

	@Override
	public void deleteCategory(Long id) throws CategoryNotFoundException {
		Category category = this.categoryRespository.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException(Helper.NO_CATEGORY_FOUND));
		this.categoryRespository.deleteById(category.getCid());
	}

}
