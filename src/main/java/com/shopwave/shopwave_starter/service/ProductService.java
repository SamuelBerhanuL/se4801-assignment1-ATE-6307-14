package com.shopwave.shopwave_starter.service;

import com.shopwave.shopwave_starter.dto.ProductDTO;
import com.shopwave.shopwave_starter.exception.ProductNotFoundException;
import com.shopwave.shopwave_starter.mapper.ProductMapper;
import com.shopwave.shopwave_starter.model.Product;
import com.shopwave.shopwave_starter.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductDTO createProduct(ProductDTO dto) {
        Product product = ProductMapper.toEntity(dto);
        Product saved = repository.save(product);
        return ProductMapper.toDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductDTO getProductById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return ProductMapper.toDTO(product);
    }

    public ProductDTO updateStock(Long id, int delta) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        int newStock = product.getStock() + delta;

        if (newStock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }

        product.setStock(newStock);

        return ProductMapper.toDTO(repository.save(product));
    }
}
