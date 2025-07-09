package org.example;


public class OrderHistory {
    private final int processId;
    private final Product product;
    private final int quantity;
    private final String comment;
    private final String createdAt;
    private final String updatedAt;

    public OrderHistory(int processId, Product product, int quantity, String comment, String createdAt, String updatedAt) {
        this.processId = processId;
        this.product = product;
        this.quantity = quantity;
        this.comment = comment;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "OrderHistory{" +
                "processId=" + processId +
                ", product=" + product +
                ", quantity=" + quantity +
                ", comment='" + comment + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }

    public int getProcessId() {
        return processId;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getComment() {
        return comment;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}