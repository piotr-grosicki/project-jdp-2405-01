package com.kodilla.ecommercee.entity;

import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.GroupRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class ProductTestSuite {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GroupRepository groupRepository;

    @Test
    void shouldGetAllProducts() {
        //Given
        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        Product product4 = new Product();

        product1.setName("p1");
        product2.setName("p2");
        product3.setName("p3");
        product4.setName("p4");

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);

        //When
        List<Product> products = productRepository.findAll();

        //Then
        assertEquals(4, products.size());

        //Cleanup
        productRepository.delete(product1);
        productRepository.delete(product2);
        productRepository.delete(product3);
        productRepository.delete(product4);
    }

    @Test
    void shouldGetOneProduct() {
        //Given
        Product product = new Product();
        product.setName("p1");
        productRepository.save(product);

        //When
        Optional<Product> fetchedProduct = productRepository.findById(product.getId());

        //Then
        assertTrue(fetchedProduct.isPresent());

        //Cleanup
        productRepository.delete(product);
    }

    @Test
    void shouldCreateNewProduct() {
        //Given
        Group group = new Group();
        group.setName("Test Group");
        groupRepository.save(group);

        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(BigDecimal.TEN);
        product.setQuantity(1);
        product.setGroup(group);
        productRepository.save(product);

        //When
        Optional<Product> fetchedProduct = productRepository.findById(product.getId());

        //Then
        assertTrue(fetchedProduct.isPresent());
        assertEquals("Test Product", fetchedProduct.get().getName());

        //Cleanup
        productRepository.delete(product);
        groupRepository.delete(group);
    }

    @Test
    void shouldUpdateProduct() {
        //Given
        Group group = new Group();
        group.setName("Test Group");
        groupRepository.save(group);

        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(BigDecimal.TEN);
        product.setQuantity(1);
        product.setGroup(group);
        productRepository.save(product);

        //When
        product.setName("Updated Product");
        productRepository.save(product);

        Optional<Product> fetchedProduct = productRepository.findById(product.getId());

        //Then
        assertTrue(fetchedProduct.isPresent());
        assertEquals("Updated Product", fetchedProduct.get().getName());

        //Cleanup
        productRepository.delete(product);
        groupRepository.delete(group);
    }

    @Test
    void shouldDeleteOneProduct() {
        //Given
        Group group = new Group();
        group.setName("Test Group");
        groupRepository.save(group);

        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(BigDecimal.TEN);
        product.setQuantity(1);
        product.setGroup(group);
        productRepository.save(product);

        //When
        Long productId = product.getId();
        productRepository.deleteById(productId);
        Optional<Product> fetchedProduct = productRepository.findById(productId);

        //Then
        assertFalse(fetchedProduct.isPresent());

        //Cleanup
        groupRepository.delete(group);
    }
}
