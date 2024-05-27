package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dto.request.CreateProductRequest;
import com.kodilla.ecommercee.dto.request.UpdateProductRequest;
import com.kodilla.ecommercee.dto.response.ProductResponse;
import com.kodilla.ecommercee.entity.Group;
import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.exception.NegativeValuesException;
import com.kodilla.ecommercee.exception.NullValueException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final GroupRepository groupRepository;
    private final ProductMapper productMapper;

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.mapToProductListResponse(products);
    }

    public ProductResponse getProduct(Long id) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return productMapper.mapToProductResponse(product);
    }

    public ProductResponse addProduct(CreateProductRequest createProductRequest) throws GroupNotFoundException, NullValueException, NegativeValuesException {
        Group group = groupRepository.findById(createProductRequest.groupId()).orElseThrow(() -> new GroupNotFoundException(createProductRequest.groupId()));

        if (createProductRequest.name() == null || createProductRequest.description() == null ||
                createProductRequest.price() == null || createProductRequest.quantity() == null) {
            throw new NullValueException();
        }
        if (createProductRequest.price().signum() < 0 || createProductRequest.quantity() < 0) {
            throw new NegativeValuesException();
        }

        Product product = new Product(createProductRequest.name(), createProductRequest.description(), createProductRequest.price(), createProductRequest.quantity(), group);
        productRepository.save(product);
        return productMapper.mapToProductResponse(product);
    }

    public ProductResponse updateProduct(UpdateProductRequest updateProductRequest) throws ProductNotFoundException, GroupNotFoundException, NullValueException, NegativeValuesException {
        Product product = productRepository.findById(updateProductRequest.id()).orElseThrow(() -> new ProductNotFoundException(updateProductRequest.id()));
        Group group = groupRepository.findById(updateProductRequest.groupId()).orElseThrow(() -> new GroupNotFoundException(updateProductRequest.groupId()));
        boolean updated = false;

        if (updateProductRequest.name() == null || updateProductRequest.description() == null ||
                updateProductRequest.price() == null || updateProductRequest.quantity() == null) {
            throw new NullValueException();
        }
        if (updateProductRequest.price().signum() < 0 || updateProductRequest.quantity() < 0) {
            throw new NegativeValuesException();
        }
        if (!updateProductRequest.name().equals(product.getName())) {
            product.setName(updateProductRequest.name());
            updated = true;
        }
        if (!updateProductRequest.description().equals(product.getDescription())) {
            product.setDescription(updateProductRequest.description());
            updated = true;
        }
        if (!updateProductRequest.price().equals(product.getPrice())) {
            product.setPrice(updateProductRequest.price());
            updated = true;
        }
        if (!updateProductRequest.quantity().equals(product.getQuantity())) {
            product.setQuantity(updateProductRequest.quantity());
            updated = true;
        }
        if (!group.equals(product.getGroup())) {
            product.setGroup(group);
            updated = true;
        }
        if (updated) {
            productRepository.save(product);
        }
        return productMapper.mapToProductResponse(product);
    }

    public void deleteProduct(Long id) throws ProductNotFoundException {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
    }
}
