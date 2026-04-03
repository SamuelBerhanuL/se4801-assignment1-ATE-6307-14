package com.shopwave.shopwave_starter.repository;

import com.shopwave.shopwave_starter.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);

    List<Product> findByNameContainingIgnoreCase(String keyword);
}
