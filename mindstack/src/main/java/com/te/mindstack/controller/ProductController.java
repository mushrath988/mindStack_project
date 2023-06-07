package com.te.mindstack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.mindstack.dto.ProductDto;
import com.te.mindstack.dto.ProductResponseDto;
import com.te.mindstack.response.AppResponse;
import com.te.mindstack.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/addProduct")
	public ResponseEntity<AppResponse> createProduct(@ModelAttribute ProductDto productDto) {
		ProductResponseDto prDto = productService.createProducts(productDto);
		if (prDto != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new AppResponse(false, "product added", 201, prDto));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new AppResponse(false, "something went wrong", 400, null));
		}
	}

	@PutMapping("/updateProduct")
	public ResponseEntity<AppResponse> updateProduct(@ModelAttribute ProductDto productDto) {
		ProductResponseDto proDto = productService.updateProducts(productDto);
		if (proDto != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new AppResponse(false, "update successful", 200, proDto));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new AppResponse(false, "something went wrong", 400, null));
		}
	}

	@GetMapping("/getAllProducts")
	public ResponseEntity<AppResponse> getAll() {
		List<ProductResponseDto> proDto = productService.getAllProducts();
		if (proDto != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new AppResponse(false, "got all products", 200, proDto));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new AppResponse(false, "something went wrong", 400, null));
		}
	}

	@GetMapping("/getProduct/{productId}")
	public ResponseEntity<AppResponse> getProductById(@PathVariable int productId) {
		ProductResponseDto proDto = productService.getProductById(productId);
		if (proDto != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new AppResponse(false, "Product fetched successfully", 200, proDto));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new AppResponse(false, "something went wrong", 400, null));
		}
	}

	@GetMapping("/productSearch/{search}")
	public ResponseEntity<AppResponse> productSearch(@PathVariable String search) {
		List<ProductResponseDto> prDto = productService.productSearch(search);
		if (prDto != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new AppResponse(false, "Products fetched successfully", 200, prDto));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new AppResponse(false, "something went wrong", 400, null));
		}
	}

	@DeleteMapping("/deleteProduct/{productId}")
	public ResponseEntity<AppResponse> deleteProduct(@PathVariable int productId) {
		Boolean product = productService.deleteProducts(productId);
		if (product) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new AppResponse(false, "product deleted", 200, product));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new AppResponse(false, "something went wrong", 400, null));
		}
	}

	@GetMapping("/filter/{minPrice}/{maxPrice}")
	public ResponseEntity<AppResponse> filterBasedOnPrice(@PathVariable double minPrice,
			@PathVariable double maxPrice) {
		List<ProductResponseDto> prDto = productService.filterBasedOnPrice(minPrice, maxPrice);
		if (prDto != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new AppResponse(false, "Products fetched successfully", 200, prDto));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new AppResponse(false, "something went wrong", 400, null));
		}
	}
}
