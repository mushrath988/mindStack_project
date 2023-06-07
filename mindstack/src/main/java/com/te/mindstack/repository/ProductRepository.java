package com.te.mindstack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.te.mindstack.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT u FROM Product u WHERE u.title LIKE %?1% OR u.description LIKE %?1%")
	List<Product> searchProductBasedOnTitleOrDescription(String search);

	List<Product> findByPriceGreaterThanEqualAndPriceLessThanEqual(double minPrice, double maxPrice);
}
