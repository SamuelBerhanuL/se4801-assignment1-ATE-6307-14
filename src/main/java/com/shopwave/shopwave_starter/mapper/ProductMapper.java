package com.shopwave.shopwave_starter.mapper;

import com.shopwave.shopwave_starter.dto.ProductDTO;
import com.shopwave.shopwave_starter.model.Product;

public class ProductMapper {

    public static ProductDTO toDTO(Product product) {

        if (product == null) return null;

        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .categoryId(product.getCategory() != null ? product.getCategory().getId() : null)
                .categoryName(product.getCategory() != null ? product.getCategory().getName() : null)
                .build();
    }

    public static Product toEntity(ProductDTO dto) {

        if (dto == null) return null;

        Product product = new Product();

        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());

        // NOTE: Category mapping handled in service

        return product;
    }
}