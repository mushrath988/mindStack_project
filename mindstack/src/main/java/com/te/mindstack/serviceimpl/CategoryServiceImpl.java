package com.te.mindstack.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.mindstack.dto.CategoryDto;
import com.te.mindstack.dto.CategoryResponseDto;
import com.te.mindstack.dto.ProductDto;
import com.te.mindstack.entity.Category;
import com.te.mindstack.entity.Product;
import com.te.mindstack.exception.ResourceNotFoundException;
import com.te.mindstack.repository.CategoryRepository;
import com.te.mindstack.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public CategoryResponseDto addCategories(CategoryDto categoryDto) {
		Category category = new Category();
		CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
		BeanUtils.copyProperties(categoryDto, category);
		categoryRepository.save(category);
		BeanUtils.copyProperties(category, categoryResponseDto);
		return categoryResponseDto;
	}

	@Override
	public boolean deleteCategories(Integer categoryId) {
		Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
		if (optionalCategory.isPresent()) {
			Category category = optionalCategory.get();
			categoryRepository.delete(category);
			return true;
		}
		throw new ResourceNotFoundException("Id is not found");
	}

	@Override
	public List<CategoryResponseDto> getAllCategories() {
		List<Category> category = categoryRepository.findAll();
		List<CategoryResponseDto> categoryDtoList = new ArrayList<>();
		if (!category.isEmpty()) {
			category.forEach(categories -> {
				CategoryResponseDto categoryDto = new CategoryResponseDto();
				BeanUtils.copyProperties(categories, categoryDto);
				categoryDtoList.add(categoryDto);
			});
		}
		return categoryDtoList;
	}

	@Override
	public CategoryResponseDto updateCategories(Integer categoryId, CategoryDto categoryDto) {
		Category oldCategory = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Id is not found"));
		BeanUtils.copyProperties(categoryDto, oldCategory);
		categoryRepository.save(oldCategory);
		CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
		BeanUtils.copyProperties(oldCategory, categoryResponseDto);
		return categoryResponseDto;
	}

	@Override
	public List<CategoryResponseDto> filterBasedOnCategory(String categorySearch) {
		Optional<Category> optionalCategory = categoryRepository.findByCategoryNameIgnoreCase(categorySearch);
		List<CategoryResponseDto> categoryDtos = new ArrayList<>();
		if (optionalCategory.isPresent()) {
			CategoryResponseDto categoryDto = new CategoryResponseDto();
			Category category = optionalCategory.get();
			BeanUtils.copyProperties(category, categoryDto);
			List<Product> products = category.getProducts();
			List<ProductDto> productDtos = new ArrayList<>();
			products.forEach(product -> {
				ProductDto productDto = new ProductDto();
				BeanUtils.copyProperties(product, productDto);
				productDtos.add(productDto);
			});
			categoryDto.setProductDto(productDtos);
			categoryDtos.add(categoryDto);
		}
		return categoryDtos;
	}
}
