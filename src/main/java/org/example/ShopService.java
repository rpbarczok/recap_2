package org.example;

import java.math.BigDecimal;
import java.util.Map;

public class ShopService {
    private final OrderRepo orderRepo;

    public ShopService() {
        this.orderRepo = new OrderListRepo(new ProductRepo());
    }

    public ShopService(ProductRepo productRepo) {
        this.orderRepo = new OrderListRepo(productRepo);
    }

    public ShopService(OrderRepo orderRepo) {
            this.orderRepo = orderRepo;
    }

    public OrderRepo getOrderRepo() {
        return orderRepo;
    }

    public Order addOrder(int productId, int quantity) {
        if (orderRepo.getProductRepo().getProductById(productId) == null) {
            System.out.println("Product with id " + productId + " not found");
            return null;
        } else {
            return  orderRepo.addOrder(productId, quantity);
        }

    }

    public Order updateOrder(int orderId, int quantity) {
        Order order = orderRepo.getOrderById(orderId);
        order.updateQuantity(quantity);
        return orderRepo.getOrderById(orderId);
    }

    public Order updateOrder(int orderId, String comment) {
        Order order = orderRepo.getOrderById(orderId);
        order.updateComment(comment);
        return orderRepo.getOrderById(orderId);
    }

    public Order updateStatus(int orderId, Status status) {
        Order order = orderRepo.getOrderById(orderId);
        order.updateStatus(status);
        return orderRepo.getOrderById(orderId);
    }

    public Product addNewProduct(String name, BigDecimal price) {
        return orderRepo.addProduct(name, price);
    }

    public Map<Integer, Product> getProducts() {
        return orderRepo.getProductRepo().getProducts();
    }

}
