package com.te.mindstack.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductResponseDto {

	private int productId;
	private String title;
	private String description;
	private double price;
	private int quantity;
	private CategoryResponseDto category;
	private String imagePath;
}
