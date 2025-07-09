package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {
    BigDecimal price = new BigDecimal("12.99");
    BigDecimal price2 = new BigDecimal("0.11");
    ProductRepo productRepo = new ProductRepo();
    Product product = new Product(1,    "Hammer", price);

    @BeforeEach
    void setup() {
        productRepo.addProduct("Hammer", price);
        productRepo.addProduct("Zange", price);
        productRepo.addProduct("Nagel", price2);
    }

    @Test
    void addOrder_should_return_order_when_product_exists() {
        ShopService shopService = new ShopService(productRepo);
        Order expected = new Order(1, product, 1);

        Order result = shopService.addOrder(1, 1);
    }

    @Test
    void addOrder_should_return_null_when_product_does_not_exist() {
        ShopService shopService = new ShopService(productRepo);

        Order result = shopService.addOrder(17, 1);
        assertNull(result);
    }
    
}