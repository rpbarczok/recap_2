package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

        try {
            Order result = shopService.addOrder(1, 1);
            assertEquals(expected, result);
        } catch (Exception e) {
            fail();
        }

    }

    @Test
    void addOrder_should_throw_exception_when_product_does_not_exist() {
        ShopService shopService = new ShopService(productRepo);
        try {
            Order result = shopService.addOrder(17, 1);
            fail();
        } catch (Exception e) {
            //
        }

    }

    @Test
    void showOrderByStatus_returns_right_Orders() {
        ShopService shopService = new ShopService(productRepo);

        try {
            Order order1 = shopService.addOrder(1, 1);
            Order order2 = shopService.addOrder(2, 2);
            Order order3 = shopService.addOrder(3, 30);

            shopService.updateOrder(1,5);
            shopService.updateOrder(1,Status.IN_DELIVERY);
            shopService.updateOrder(2,Status.IN_DELIVERY);

            Map<Integer, Order> expected = new HashMap<Integer, Order>();
            expected.put(1, order1);
            expected.put(2, order2);
            Map<Integer, Order> actual = shopService.showOrdersByStatus(Status.IN_DELIVERY);

            assertEquals(expected, actual);
        } catch (Exception e) {
            fail();
        }


    }
}