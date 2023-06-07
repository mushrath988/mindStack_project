package com.te.mindstack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.te.mindstack.dto.CategoryDto;
import com.te.mindstack.dto.CategoryResponseDto;
import com.te.mindstack.response.AppResponse;
import com.te.mindstack.service.CategoryService;

@RestController
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@PostMapping("/createCategory")
	public ResponseEntity<AppResponse> addCategory(@RequestBody CategoryDto categoryDto) {
		CategoryResponseDto caDto = categoryService.addCategories(categoryDto);
		if (caDto != null) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new AppResponse(false, "category created successfully", 200, caDto));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new AppResponse(true, "something went wrong", 400, null));
		}
	}

	@PutMapping("/updateCategory/{categoryId}")
	public ResponseEntity<AppResponse> updateCategory(@PathVariable Integer categoryId,
			@RequestBody CategoryDto categoryDto) {
		CategoryResponseDto caDto = categoryService.updateCategories(categoryId, categoryDto);
		if (caDto != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new AppResponse(false, "category updated successfully", 200, categoryDto));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new AppResponse(true, "something went wrong", 400, null));
		}
	}

	@GetMapping("/getAllCategory")
	public ResponseEntity<AppResponse> getCategory() {
		List<CategoryResponseDto> caDtoList = categoryService.getAllCategories();
		if (caDtoList != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new AppResponse(false, "got all categoryList", 200, caDtoList));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new AppResponse(true, "something went wrong", 400, null));
		}
	}

	@DeleteMapping("/deleteCategory/{categoryId}")
	public ResponseEntity<AppResponse> deleteCategory(@PathVariable Integer categoryId) {
		boolean isCategoryDeleted = categoryService.deleteCategories(categoryId);
		if (isCategoryDeleted) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new AppResponse(false, "category deleted successfully", 200, isCategoryDeleted));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new AppResponse(true, "category not found", 400, null));
		}
	}
	
	@GetMapping("/filter/{category}")
	public ResponseEntity<AppResponse> filterBasedOnCategory(@PathVariable String category) {
		List<CategoryResponseDto> caDtoList = categoryService.filterBasedOnCategory(category);
		if (caDtoList != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new AppResponse(false, "got all categoryList", 200, caDtoList));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new AppResponse(true, "something went wrong", 400, null));
		}
	}

}
