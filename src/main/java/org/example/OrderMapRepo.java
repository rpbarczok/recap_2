package org.example;

import lombok.Getter;

import javax.swing.plaf.BorderUIResource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class OrderMapRepo implements OrderRepo {
    private Integer orderCounter = 0;
    @Getter
    private final Map<Integer, Order> orders = new HashMap<>();
    @Getter
    private final ProductRepo productRepo;

    public OrderMapRepo(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product addProduct(String name, BigDecimal price) {
        return productRepo.addProduct(name, price);
    }

    public Order addOrder(Integer productId, Integer quantity) throws Exception {
        Product product = productRepo.getProductById(productId).orElse(null);
        if (product != null) {
            orderCounter++;
            BigDecimal sum = product.price().multiply(new BigDecimal(quantity));
            Order order = new Order(orderCounter, product, quantity);
            orders.put(orderCounter,order);
            return order;
        } else {
            throw new Exception("Product not found");
        }
    }

    public Order addOrder(Order order) {
        orders.put(order.getOrderId(), order);
        return order;
    }

    public void removeOrder(Integer orderId) {
        orders.remove(orderId);
    }

    public Order getOrderById(Integer orderId) {
        return orders.get(orderId);
    }

    public Order updateOrder(int orderId, int quantity) {
        Order order = orders.get(orderId);
        order.updateOrder(quantity);
        return orders.get(orderId);
    }

    public Order updateOrder(int orderId, Status status) {
        Order order = orders.get(orderId);
        order.updateOrder(status);
        return orders.get(orderId);
    }
    public Order updateOrder(int orderId, String comment) {
        Order order = orders.get(orderId);
        order.updateOrder(comment);
        return orders.get(orderId);
    }
}
