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
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(String.format("Product with id: %d could not be found", id)));
        return productMapper.mapToProductResponse(product);
    }

    public ProductResponse addProduct(CreateProductRequest createProductRequest) throws GroupNotFoundException, NullValueException,NegativeValuesException {
        groupRepository.findById(createProductRequest.group().getId()).orElseThrow(() -> new GroupNotFoundException(String.format("Group with id: %d could not be found", createProductRequest.group().getId())));
        if (createProductRequest.name() == null || createProductRequest.description() == null ||
                createProductRequest.price() == null || createProductRequest.quantity() == null) {
            throw new NullValueException();
        }
        if(createProductRequest.price().signum() < 0 || createProductRequest.quantity() < 0){
            throw new NegativeValuesException();
        }

        Product product = new Product(createProductRequest.name(), createProductRequest.description(), createProductRequest.price(), createProductRequest.quantity(), createProductRequest.group());
        productRepository.save(product);
        return productMapper.mapToProductResponse(product);
    }

    public ProductResponse updateProduct(UpdateProductRequest updateProductRequest) throws ProductNotFoundException, NullValueException,NegativeValuesException {
        Product product = productRepository.findById(updateProductRequest.id()).orElseThrow(() -> new ProductNotFoundException(String.format("Product with id: %d could not be found", updateProductRequest.id())));
        if (updateProductRequest.name() == null || updateProductRequest.description() == null ||
                updateProductRequest.price() == null || updateProductRequest.quantity() == null) {
            throw new NullValueException();
        }
        if(updateProductRequest.price().signum() < 0 || updateProductRequest.quantity() < 0){
            throw new NegativeValuesException();
        }
        product.setName(updateProductRequest.name());
        product.setDescription(updateProductRequest.description());
        product.setPrice(updateProductRequest.price());
        product.setQuantity(updateProductRequest.quantity());
        product.setGroup(updateProductRequest.group());
        productRepository.save(product);
        return productMapper.mapToProductResponse(product);
    }

    public void deleteProduct(Long id) throws ProductNotFoundException {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(String.format("Product with id: %d could not be found", id));
        }
        productRepository.deleteById(id);
    }
}
