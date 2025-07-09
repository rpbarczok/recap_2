package org.example;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepoTest {

    @Test
    void addProduct_returns_product() {

        Product product1 = new Product(1,"Hammer", new BigDecimal("12.99"));
        ProductRepo productRepo = new ProductRepo();

        Product result = productRepo.addProduct("Hammer", new BigDecimal("12.99"));
        assertEquals(product1, result);

    }

    @Test
    void getProductById_returns_correctProduct() {

        Product product = new Product(2, "Zange", new BigDecimal("12.99"));
        ProductRepo productRepo = new ProductRepo();

        productRepo.addProduct("Hammer", new BigDecimal("12.99"));
        productRepo.addProduct("Zange", new BigDecimal("12.99"));

        Product result = productRepo.getProductById(2);

        assertEquals(product, result);

    }

    @Test
    void addProducts_adds_products_to_ProductRepo() {
        BigDecimal price = new BigDecimal("12.99");
        Product product1 = new Product(1,"Hammer", price);
        Product product2 = new Product(2,"Zange", price);
        Map<Integer, Product> productsTest = new HashMap<Integer, Product>();
        productsTest.put(1, product1);
        productsTest.put(2, product2);

        ProductRepo productRepo = new ProductRepo();


        productRepo.addProduct("Hammer", price);
        productRepo.addProduct("Zange", price);

        assertEquals(productRepo.getProducts(), productsTest);
    }
}