package com.te.mindstack.serviceimpl;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.te.mindstack.dto.CategoryResponseDto;
import com.te.mindstack.dto.ProductDto;
import com.te.mindstack.dto.ProductResponseDto;
import com.te.mindstack.entity.Category;
import com.te.mindstack.entity.Product;
import com.te.mindstack.exception.ResourceNotFoundException;
import com.te.mindstack.repository.CategoryRepository;
import com.te.mindstack.repository.ProductRepository;
import com.te.mindstack.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Value("${file.upload.location}")
	private String dir;

	private Path getPath(String fileName) {
		return Paths.get(this.dir + "\\" + fileName).toAbsolutePath().normalize();
	}

	private String addFile(String folderName, MultipartFile multipartFile) {
		try {
			Path dirLocation = getPath(folderName);
			if (multipartFile != null) {
				Files.createDirectories(dirLocation);
				String filename = multipartFile.getOriginalFilename();
				Path filePath = dirLocation.resolve(filename);
				Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
				return filePath.toString();
			} else {
				throw new FileNotFoundException("file Not Found");
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception.getMessage());
		}
	}

	@Override
	public ProductResponseDto createProducts(ProductDto productDto) {
		Product product = new Product();
		BeanUtils.copyProperties(productDto, product);
		ProductResponseDto productResponseDto = new ProductResponseDto();
		if (categoryRepository.findById(productDto.getCategoryId()).isPresent()) {
			Category dBCategory = categoryRepository.findById(productDto.getCategoryId()).get();
			product.setCategory(dBCategory);
			product.setImagePath(addFile("productImage", productDto.getImage()));
			productRepository.save(product);
			BeanUtils.copyProperties(product, productResponseDto);
		} else {
			throw new IllegalArgumentException("Invalid category Id");
		}
		return productResponseDto;
	}

	@Override
	public List<ProductResponseDto> getAllProducts() {
		List<Product> allProducts = productRepository.findAll();
		List<ProductResponseDto> proDtos = new ArrayList<>();
		allProducts.forEach(product -> {
			ProductResponseDto productDto = new ProductResponseDto();
			BeanUtils.copyProperties(product, productDto);
			Category category = product.getCategory();
			CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
			BeanUtils.copyProperties(category, categoryResponseDto);
			productDto.setCategory(categoryResponseDto);
			proDtos.add(productDto);
		});
		return proDtos;
	}

	@Override
	public ProductResponseDto getProductById(int productId) {
		Optional<Product> OptProduct = productRepository.findById(productId);
		if (OptProduct.isPresent()) {
			ProductResponseDto productDto = new ProductResponseDto();
			Product product = OptProduct.get();
			BeanUtils.copyProperties(product, productDto);
			Category category = product.getCategory();
			CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
			BeanUtils.copyProperties(category, categoryResponseDto);
			productDto.setCategory(categoryResponseDto);
			return productDto;
		}
		throw new ResourceNotFoundException("Invalid Product Id");
	}

	@Override
	public ProductResponseDto updateProducts(ProductDto productDto) {
		Optional<Product> OptProduct = productRepository.findById(productDto.getProductId());
		ProductResponseDto responseDto = new ProductResponseDto();
		if (OptProduct.isPresent()) {
			Product product = OptProduct.get();
			BeanUtils.copyProperties(productDto, product);
			Category category = categoryRepository.findById(productDto.getCategoryId())
					.orElseThrow(() -> new ResourceNotFoundException("Invalid Category Id"));
			product.setCategory(category);
			productRepository.save(product);
			BeanUtils.copyProperties(product, responseDto);
			return responseDto;
		}
		throw new ResourceNotFoundException("Invalid Product Id");
	}

	@Override
	public Boolean deleteProducts(int productId) {
		Optional<Product> OptProduct = productRepository.findById(productId);
		if (OptProduct.isPresent()) {
			productRepository.deleteById(productId);
			return true;
		}
		throw new ResourceNotFoundException("Invalid Product Id");
	}

	@Override
	public List<ProductResponseDto> productSearch(String search) {
		List<Product> product = productRepository.searchProductBasedOnTitleOrDescription(search);
		List<ProductResponseDto> productDtos = new ArrayList<>();
		product.forEach(prod -> {
			ProductResponseDto productDto = new ProductResponseDto();
			BeanUtils.copyProperties(prod, productDto);
			Category category = prod.getCategory();
			CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
			BeanUtils.copyProperties(category, categoryResponseDto);
			productDto.setCategory(categoryResponseDto);
			productDtos.add(productDto);
		});
		return productDtos;
	}

	@Override
	public List<ProductResponseDto> filterBasedOnPrice(double minPrice, double maxPrice) {
		List<Product> product = productRepository.findByPriceGreaterThanEqualAndPriceLessThanEqual(minPrice, maxPrice);
		List<ProductResponseDto> productDtos = new ArrayList<>();
		product.forEach(prod -> {
			ProductResponseDto productDto = new ProductResponseDto();
			BeanUtils.copyProperties(prod, productDto);
			productDtos.add(productDto);
		});
		return productDtos;
	}

}
