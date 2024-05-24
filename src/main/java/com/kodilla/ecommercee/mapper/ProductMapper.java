package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.response.ProductResponse;
import com.kodilla.ecommercee.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductMapper {

    public ProductResponse mapToProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getGroup());
    }

    public List<ProductResponse> mapToProductListResponse(List<Product> productList) {
        return productList.stream()
                .map(this::mapToProductResponse)
                .toList();
    }
}
