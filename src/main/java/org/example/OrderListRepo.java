package org.example;
import java.math.BigDecimal;
import java.util.*;

public class OrderListRepo implements OrderRepo {
    private Integer orderCounter = 0;
    private final List<Order> orders = new ArrayList<>();
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

    public ProductRepo getProductRepo() {
        return productRepo;
    }

    public Product addProduct(String name, BigDecimal price) {
        return productRepo.addProduct(name, price);
    }

    public Order addOrder(Integer productId, Integer quantity) {
        orderCounter++;
        Product product = productRepo.getProductById(productId);
        BigDecimal sum = product.price().multiply(new BigDecimal(quantity));
        Order order = new Order(orderCounter, product, quantity);
        orders.add(order);
        return order;
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

}
