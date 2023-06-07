package com.te.mindstack.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductDto {

	private int productId;
	private String title;
	private String description;
	private double price;
	private int quantity;
	private MultipartFile image;
	private int categoryId;

}