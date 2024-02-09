package com.example.EcommerceApplication.repository;

import com.example.EcommerceApplication.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
