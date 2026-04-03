package com.shopwave.shopwave_starter.service;

import com.shopwave.shopwave_starter.exception.ProductNotFoundException;
import com.shopwave.shopwave_starter.model.Product;
import com.shopwave.shopwave_starter.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product createProduct(Product product) {
        return repository.save(product);
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product updateStock(Long id, int delta) {
        Product product = getProductById(id);

        int newStock = product.getStock() + delta;

        if (newStock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }

        product.setStock(newStock);
        return repository.save(product);
    }
}
