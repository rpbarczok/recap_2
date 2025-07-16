package org.example;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShopService {
    private final OrderRepo orderRepo;

    public ShopService() {
        this.orderRepo = new OrderMapRepo(new ProductRepo());
    }

    public ShopService(ProductRepo productRepo) {
        this.orderRepo = new OrderMapRepo(productRepo);
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
        return orderRepo.updateOrder(orderId, quantity);
    }

    public Order updateOrder(int orderId, String comment) {
        return orderRepo.updateOrder(orderId, comment);
    }

    public Order updateOrder(int orderId, Status status) {
        return orderRepo.updateOrder(orderId, status);
    }

    public Product addNewProduct(String name, BigDecimal price) {
        return orderRepo.addProduct(name, price);
    }

    public Map<Integer, Product> getProducts() {
        return orderRepo.getProductRepo().getProducts();
    }

    public Map<Integer, Order> showOrdersByStatus(Status state) {
        Map<Integer,Order> orderMap = orderRepo.getOrders();
        return orderMap.entrySet().stream().filter(e -> e.getValue().getLatestOrderHistory().status() == state).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
