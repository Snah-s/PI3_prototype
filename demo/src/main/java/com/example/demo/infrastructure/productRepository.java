package com.example.demo.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.product;

public interface productRepository extends JpaRepository<product, Long> {
}
