package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.request.CreateProductRequest;
import com.kodilla.ecommercee.dto.request.DeleteProductRequest;
import com.kodilla.ecommercee.dto.request.UpdateProductRequest;
import com.kodilla.ecommercee.dto.response.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("shop/v1/product")
public class ProductController {

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(new ProductResponse(id, "sample product name", "sample product description", BigDecimal.ZERO, 0));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody CreateProductRequest createProductRequest) {
        return ResponseEntity.ok(new ProductResponse(2L, "created new product", "new product description", BigDecimal.ZERO, 0));
    }

    @PutMapping
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody UpdateProductRequest updateProductRequest) {
        return ResponseEntity.ok(new ProductResponse(2L, "updated product", "updated description", BigDecimal.ZERO, 0));
    }

    @DeleteMapping
    public ResponseEntity<ProductResponse> deleteProduct(@RequestBody DeleteProductRequest deleteProductRequest) {
        return ResponseEntity.ok(new ProductResponse(2L, "deleting product", "deleting description", BigDecimal.ZERO, 0));
    }
}
