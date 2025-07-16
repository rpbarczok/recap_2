package org.example;

import lombok.With;

@With
public record OrderHistory (int processId, Product product, int quantity, String comment, String updatedAt, Status status) {

}