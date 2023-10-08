package com.example.demo.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {}