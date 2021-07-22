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

import com.exam.dto.CategoryDto;
import com.exam.entities.Category;
import com.exam.exceptions.CategoryNotFoundException;
import com.exam.exceptions.validations.ValidateCategoryException;
import com.exam.helpers.SuccessMessage;
import com.exam.services.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@PostMapping("/create")
	public SuccessMessage createCategory(@Valid @RequestBody CategoryDto categoryDto, BindingResult br)
			throws ValidateCategoryException {
		if (br.hasErrors()) {
			throw new ValidateCategoryException(br.getFieldErrors());
		}
		Category category = this.categoryService.addCategory(categoryDto);
		return new SuccessMessage("Category " + category.getTitle() + " added successfully !");

	}

	@PutMapping("/update/{id}")
	public SuccessMessage updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("id") String id,
			BindingResult br) throws ValidateCategoryException, CategoryNotFoundException, NumberFormatException {
		if (br.hasErrors()) {
			throw new ValidateCategoryException(br.getFieldErrors());
		}
		Category updatedCategory = this.categoryService.updateCategory(Long.parseLong(id), categoryDto);
		return new SuccessMessage("Category " + updatedCategory.getCid() + " updated successfully !");
	}

	@GetMapping("/get/all")
	public Set<Category> getAllCategories() throws CategoryNotFoundException {
		return this.categoryService.getCategories();
	}

	@GetMapping("/get/{id}")
	public Category getCategory(@PathVariable("id") String id) throws NumberFormatException, CategoryNotFoundException {
		return this.categoryService.getCategory(Long.parseLong(id));
	}

	@DeleteMapping("/delete/{id}")
	public SuccessMessage deleteCategory(@PathVariable("id") String id)
			throws NumberFormatException, CategoryNotFoundException {
		this.categoryService.deleteCategory(Long.parseLong(id));
		return new SuccessMessage("Category id: " + id + " deleted successfully !");
	}

}
