package com.kodilla.ecommercee.entity;

import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    @Test
    void shouldCreateNewGroup() {
        //Given
        Group group = new Group(1L, "food", new ArrayList<>());
        groupRepository.save(group);

        //When
        Optional<Group> expectedGroup = groupRepository.findById(group.getId());

        //Then
        assertTrue(expectedGroup.isPresent());
        assertEquals("food", expectedGroup.get().getName());
        assertEquals(0, expectedGroup.get().getProductList().size());

        //cleanup
        groupRepository.delete(group);
    }

    @Test
    void shouldGetAllGroups() {
        //Given
        Group group1 = new Group();
        Group group2 = new Group();
        Group group3 = new Group();
        Group group4 = new Group();

        groupRepository.save(group1);
        groupRepository.save(group2);
        groupRepository.save(group3);
        groupRepository.save(group4);

        //When
        List<Group> groupList = groupRepository.findAll();

        //Then
        assertEquals(4, groupList.size());

        //Cleanup
        groupRepository.delete(group1);
        groupRepository.delete(group2);
        groupRepository.delete(group3);
        groupRepository.delete(group4);
    }

    @Test
    void shouldGetOneGroupById() {
        //Given
        Group group1 = new Group();
        Group group2 = new Group();
        Group group3 = new Group();
        group3.setName("myGroup");
        Group group4 = new Group();

        groupRepository.save(group1);
        groupRepository.save(group2);
        groupRepository.save(group3);
        groupRepository.save(group4);

        //When
        Optional<Group> expectedGroup = groupRepository.findById(group3.getId());

        //Then
        assertTrue(expectedGroup.isPresent());
        assertEquals("myGroup", expectedGroup.get().getName());


        //Cleanup
        groupRepository.delete(group1);
        groupRepository.delete(group2);
        groupRepository.delete(group3);
        groupRepository.delete(group4);
    }


    @Test
    void shouldUpdateGroup() {
        //Given
        Group group = new Group(1L, "food", new ArrayList<>());
        groupRepository.save(group);

        Product product1 = new Product();
        product1.setName("Test Product");
        product1.setDescription("Test Description");
        product1.setGroup(group);

        Product product2 = new Product();
        product2.setName("Test Product");
        product2.setDescription("Test Description");
        product2.setGroup(group);

        productRepository.save(product1);
        productRepository.save(product2);
        ArrayList<Product> productList = new ArrayList<>(List.of(product1, product2));

        //When
        group.setProductList(productList);
        group.setName("furniture");
        groupRepository.save(group);
        Optional<Group> expectedGroup = groupRepository.findById(group.getId());

        //Then
        assertEquals("furniture", expectedGroup.get().getName());
        assertEquals(2, expectedGroup.get().getProductList().size());

        //cleanup
        productRepository.delete(product1);
        productRepository.delete(product2);
        groupRepository.delete(group);
    }

    @Test
    void shouldDeleteGroupById() {
        //Given
        Group group1 = new Group();
        Group group2 = new Group();
        Group group3 = new Group();
        Group group4 = new Group();

        groupRepository.save(group1);
        groupRepository.save(group2);
        groupRepository.save(group3);
        groupRepository.save(group4);

        //When
        groupRepository.deleteById(group2.getId());
        List<Group> expectedGroupList = groupRepository.findAll();

        //Then
        assertEquals(3, expectedGroupList.size());

        //cleanup
        groupRepository.delete(group1);
        groupRepository.delete(group2);
        groupRepository.delete(group3);
        groupRepository.delete(group4);
    }
}
