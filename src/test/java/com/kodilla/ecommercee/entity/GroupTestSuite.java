package com.kodilla.ecommercee.entity;

import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GroupTestSuite {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    void cleanup() {
        productRepository.deleteAll();
        groupRepository.deleteAll();
    }

    @Transactional
    @Test
    void shouldCreateNewGroup() {
        //Given
        Group group = new Group("food", List.of(new Product("Test Product", "Test Description",
                new BigDecimal(10), 3)));
        groupRepository.save(group);

        //When
        Optional<Group> expectedGroup = groupRepository.findById(group.getId());
        List<Product> expectedProduct = productRepository.findAll();

        //Then
        assertTrue(expectedGroup.isPresent());
        assertEquals("food", expectedGroup.get().getName());
        assertEquals(1, expectedProduct.size());
        assertEquals(1, expectedGroup.get().getProductList().size());

        //Cleanup with @AfterEach
    }

    @Test
    void shouldGetAllGroups() {
        //Given
        Group group1 = new Group("g1", new ArrayList<>());
        Group group2 = new Group("g2", new ArrayList<>());
        Group group3 = new Group("g3", new ArrayList<>());
        Group group4 = new Group("g4", new ArrayList<>());

        groupRepository.save(group1);
        groupRepository.save(group2);
        groupRepository.save(group3);
        groupRepository.save(group4);

        //When
        List<Group> groupList = groupRepository.findAll();

        //Then
        assertEquals(4, groupList.size());

        //Cleanup with @AfterEach
    }

    @Test
    void shouldGetOneGroupById() {
        //Given
        Group group1 = new Group("g1", new ArrayList<>());
        Group group2 = new Group("g2", new ArrayList<>());
        Group group3 = new Group("g3", new ArrayList<>());
        Group group4 = new Group("g4", new ArrayList<>());

        groupRepository.save(group1);
        groupRepository.save(group2);
        groupRepository.save(group3);
        groupRepository.save(group4);

        //When
        Optional<Group> expectedGroup = groupRepository.findById(group3.getId());

        //Then
        assertTrue(expectedGroup.isPresent());
        assertEquals("g3", expectedGroup.get().getName());

        //Cleanup with @AfterEach
    }

    @Transactional
    @Test
    void shouldUpdateGroup() {
        //Given
        Group group = new Group("food", new ArrayList<>());
        groupRepository.save(group);

        Product product1 = new Product("Test Product", "Test Description", new BigDecimal(10),
                3, group);
        Product product2 = new Product("Test Product", "Test Description", new BigDecimal(10),
                3, group);

        productRepository.save(product1);
        productRepository.save(product2);
        ArrayList<Product> productList = new ArrayList<>(List.of(product1, product2));

        //When
        group.setProductList(productList);
        group.setName("furniture");
        groupRepository.save(group);
        Optional<Group> expectedGroup = groupRepository.findById(group.getId());

        //Then
        assertTrue(expectedGroup.isPresent());
        assertEquals("furniture", expectedGroup.get().getName());
        assertEquals(2, expectedGroup.get().getProductList().size());

        //Cleanup with @AfterEach
    }

    @Test
    void shouldDeleteGroupById() {
        //Given
        Group group1 = new Group("g1", new ArrayList<>());
        Group group2 = new Group("g2", new ArrayList<>());
        Group group3 = new Group("g3", new ArrayList<>());
        Group group4 = new Group("g4", new ArrayList<>());

        groupRepository.save(group1);
        groupRepository.save(group2);
        groupRepository.save(group3);
        groupRepository.save(group4);

        Product product1 = new Product("Test Product", "Test Description", new BigDecimal(10),
                3, group1);
        Product product2 = new Product("Test Product2", "Test Description2", new BigDecimal(15),
                3, group2);

        productRepository.save(product1);
        productRepository.save(product2);


        //When
        try {
            groupRepository.deleteById(group2.getId());
        } catch (Exception ignored) {}
        List<Group> expectedGroupListAfterUnsuccessfulDelete = groupRepository.findAll();

        product2.setGroup(null);
        productRepository.save(product2);
        groupRepository.deleteById(group2.getId());
        List<Group> expectedGroupListAfterDelete = groupRepository.findAll();

        //Then
        assertEquals(4, expectedGroupListAfterUnsuccessfulDelete.size());
        assertEquals(3, expectedGroupListAfterDelete.size());

        //Cleanup with @AfterEach
    }
}
