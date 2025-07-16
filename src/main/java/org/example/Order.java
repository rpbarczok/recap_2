package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class Order {
    private final int orderId;
    private int processId=0;
    private final String createdAt;
    private final Map<Integer, OrderHistory> orderTimeLine = new HashMap<Integer, OrderHistory>() ;

    public Order(int orderId, Product product, int quantity) {
        this.orderId = orderId;
        this.createdAt = StringDate.getCurrentDateString();
        initializeOrder(product, quantity);
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProcessId() {
        return processId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Map<Integer, OrderHistory> getOrderTimeLine() {
        return orderTimeLine;
    }

    public OrderHistory initializeOrder(Product product, int quantity) {
        processId+=1;
        OrderHistory orderHistory = new OrderHistory(processId, product, quantity, "Initial Order", null, Status.PROCESSING);
        orderTimeLine.put(processId, orderHistory);
        return orderHistory;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", processId=" + processId +
                ", orderTimeLine=" + orderTimeLine +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId && processId == order.processId && Objects.equals(orderTimeLine, order.orderTimeLine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, processId, orderTimeLine);
    }

    public OrderHistory updateQuantity(int quantity) {
        OrderHistory orderHistory = orderTimeLine.get(processId);
        processId+=1;
        OrderHistory newOrderHistory = orderHistory.withQuantity(quantity);
        OrderHistory updatedOrderHistory = orderHistory.withUpdatedAt(StringDate.getCurrentDateString());
        orderTimeLine.put(processId, updatedOrderHistory);
        return newOrderHistory;
    }

    public OrderHistory updateComment(String comment) {
        OrderHistory orderHistory = orderTimeLine.get(processId);
        processId+=1;
        OrderHistory newOrderHistory = orderHistory.withComment(comment);
        OrderHistory updatedOrderHistory = orderHistory.withUpdatedAt(StringDate.getCurrentDateString());
        orderTimeLine.put(processId, updatedOrderHistory);
        return newOrderHistory;
    }

    public OrderHistory updateStatus(Status status) {
        OrderHistory orderHistory = orderTimeLine.get(processId);
        processId+=1;
        OrderHistory newOrderHistory = orderHistory.withStatus(status);
        OrderHistory updatedOrderHistory = orderHistory.withUpdatedAt(StringDate.getCurrentDateString());
        orderTimeLine.put(processId, updatedOrderHistory);
        return newOrderHistory;
    }
}
