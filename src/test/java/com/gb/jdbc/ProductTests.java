package com.gb.jdbc;

import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductTests {
    public static H2DbHelper helper;

    @BeforeAll
    public static void beforeAll() throws Exception {
        helper = new H2DbHelper();
        helper.connect();
    }

    @Order(1)
   // @Test
    public void testGetProductById() throws Exception {
        System.out.println(helper.getProductById(helper.getFirstProductId()));
    }

    @Order(2)
   // @Test
    public void testGetProducts() throws Exception {
        List<Product> result = helper.getProducts();
        for (Product i : result) {
            System.out.println(i);
        }
    }

    @AfterAll
    public static void afterAll() throws Exception {
        helper.close();
    }

}
