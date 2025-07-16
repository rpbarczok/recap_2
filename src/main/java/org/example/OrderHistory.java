package org.example;


public record OrderHistory (int processId, Product product, int quantity, String comment, String updatedAt) {

}