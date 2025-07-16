package org.example;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.*;

public class OrderListRepo implements OrderRepo {
    private Integer orderCounter = 0;
    private final List<Order> orders = new ArrayList<>();
    @Getter
    private final ProductRepo productRepo;

    public OrderListRepo(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Map<Integer, Order> getOrders() {
        Map<Integer, Order> ordersMap = new HashMap<>();
        for (Order order : orders) {
            ordersMap.put(order.getOrderId(), order);
        }
        return ordersMap;
    }

    public Product addProduct(String name, BigDecimal price) {
        return productRepo.addProduct(name, price);
    }

    public Order addOrder(Integer productId, Integer quantity) throws Exception {
        orderCounter++;
        Product product = productRepo.getProductById(productId).orElse(null);
        if (product != null) {
            BigDecimal sum = product.price().multiply(new BigDecimal(quantity));
            Order order = new Order(orderCounter, product, quantity);
            orders.add(order);
            return order;
        } else {
            throw new Exception("Product with id " + productId + " not found!");
        }

    }

    public Order addOrder(Order order) {
        orders.add(order);
        return order;
    }

    public void removeOrder(Integer orderId) {
        orders.remove(orderId);
    }

    public Order getOrderById(Integer orderId) {
        return orders.get(orderId);
    }

    public Order updateOrder(int orderId, int quantity) {
        Order foundOrder = null;
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                order.updateOrder(quantity);
                foundOrder = orders.get(orders.indexOf(order));
            }
        }
        return foundOrder;
    }

    public Order updateOrder(int orderId, Status status) {
        Order foundOrder = null;
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                order.updateOrder(status);
                foundOrder = orders.get(orders.indexOf(order));
            }
        }
        return foundOrder;
    }

    public Order updateOrder(int orderId, String comment) {
        Order foundOrder = null;
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                order.updateOrder(comment);
                foundOrder = orders.get(orders.indexOf(order));
            }
        }
        return foundOrder;
    }
}