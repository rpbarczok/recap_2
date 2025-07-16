package org.example;

import lombok.With;

import java.math.BigDecimal;
import java.util.Map;


public interface OrderRepo {

    Order addOrder(Integer productId, Integer quantity);

    Order addOrder(Order order);

    void removeOrder(Integer orderId);

    Order getOrderById(Integer orderId);

    Map<Integer, Order> getOrders();

    ProductRepo getProductRepo();

    public Product addProduct(String name, BigDecimal price);

}
