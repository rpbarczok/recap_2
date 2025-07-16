package org.example;

import lombok.With;

import java.time.Instant;
import java.util.Date;

@With
public record OrderHistory (int processId, Product product, int quantity, String comment, Instant updatedAt, Status status) {

}