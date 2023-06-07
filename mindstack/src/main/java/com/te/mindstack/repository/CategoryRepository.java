package com.te.mindstack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.mindstack.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	Optional<Category> findByCategoryName(String name);

	Optional<Category> findByCategoryNameIgnoreCase(String categoryName);
}
