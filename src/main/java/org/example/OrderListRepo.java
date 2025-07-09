package org.example;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OrderListRepo implements OrderRepo {
    Integer orderCounter = 0;
    Map<Integer, Order> orders = new HashMap<>();
    ProductRepo productRepo;

    public OrderListRepo(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Map<Integer, Order> getOrders() {
        return orders;
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
        orders.put(orderCounter,order);
        return order;
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

}
