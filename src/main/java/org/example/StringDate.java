package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringDate {

    public static String getCurrentDateString() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return today.format(formatter);
    }
}
