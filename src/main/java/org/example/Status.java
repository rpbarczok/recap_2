package org.example;

public enum Status {
    PROCESSING("processing"),
    IN_DELIVERY("on the road"),
    COMPLETED("completed");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
