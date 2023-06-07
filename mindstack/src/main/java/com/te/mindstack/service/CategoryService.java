package com.te.mindstack.service;

import java.util.List;

import com.te.mindstack.dto.CategoryDto;
import com.te.mindstack.dto.CategoryResponseDto;

public interface CategoryService {

	CategoryResponseDto addCategories(CategoryDto categoryDto);

	boolean deleteCategories(Integer categoryId);

	List<CategoryResponseDto> getAllCategories();

	CategoryResponseDto updateCategories(Integer categoryId, CategoryDto categoryDto);

	List<CategoryResponseDto> filterBasedOnCategory(String category);

}
