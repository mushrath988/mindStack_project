package com.te.mindstack.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CategoryResponseDto {

	private int categoryId;
	private String categoryName;
	private List<ProductDto> productDto;
}
