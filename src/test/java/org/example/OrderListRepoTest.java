package org.example;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class OrderListRepoTest {
    BigDecimal price = new BigDecimal("12.99");
    BigDecimal price2 = new BigDecimal("0.11");
    ProductRepo productRepo = new ProductRepo();

    @BeforeEach
    void setup() {
        productRepo.addProduct("Hammer", price);
        productRepo.addProduct("Zange", price);
        productRepo.addProduct("Nagel", price2);
    }

    @Test
    void addOrder_returns_order() {
        OrderListRepo orderListRepo= new OrderListRepo(productRepo);

        Product product = new Product(1,"Hammer", price);
        Order expected = new Order(1, product, 1);

        Order result = orderListRepo.addOrder(1, 1);

        assertEquals(expected.toString(), result.toString());
    }

    @Test
    void addOrder_adds_order_to_list() {
        Product product = new Product(1,"Hammer", price);
        BigDecimal sum = product.price().multiply(new BigDecimal("2"));
        Order order1 = new Order(1, product, 1);
        Order order2 = new Order(2, product, 2);
        Map<Integer, Order> expected = new HashMap<Integer, Order>();
        expected.put(order1.getOrderId(), order1);
        expected.put(order2.getOrderId(), order2);

        OrderListRepo orderListRepo= new OrderListRepo(productRepo);

        orderListRepo.addOrder(1, 1);
        orderListRepo.addOrder(1, 2);

        assertEquals(expected.toString(), orderListRepo.getOrders().toString());


    }

    @Test
    void getOrderById_returns_order() {
        Product product = new Product(1,"Hammer", price);
        Order expected = new Order(1, product, 1);
        OrderListRepo orderListRepo= new OrderListRepo(productRepo);
        orderListRepo.addOrder(1, 1);
        orderListRepo.addOrder(1, 2);

        Order result = orderListRepo.getOrderById(1);

        assertEquals(expected.toString(), result.toString());
    }
}