package com.te.mindstack.service;

import java.util.List;

import com.te.mindstack.dto.ProductDto;
import com.te.mindstack.dto.ProductResponseDto;

public interface ProductService {

	ProductResponseDto createProducts(ProductDto productDto);

	List<ProductResponseDto> getAllProducts();

	ProductResponseDto getProductById(int productId);

	ProductResponseDto updateProducts(ProductDto productDto);

	Boolean deleteProducts(int productId);

	List<ProductResponseDto> productSearch(String search);

	List<ProductResponseDto> filterBasedOnPrice(double minPrice, double maxPrice);

}
