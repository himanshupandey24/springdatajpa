package com.springdatajpa.springdatajpa.repository;

import com.springdatajpa.springdatajpa.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
