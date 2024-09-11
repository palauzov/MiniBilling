package org.example.Parsers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateTimeParser {

    public LocalDate parseToLocalDate(String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format: " + e.getMessage());
        }
        return null;
    }

    public LocalDateTime parseToLocalDateTimeISO(String dateTimeString){

        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        try {
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateTimeString, formatter);
            return  offsetDateTime.toLocalDateTime();

        } catch (DateTimeParseException e) {
            System.out.println("Invalid date-time format: " + e.getMessage());
        }
        return null;
    }

    public LocalDateTime parseToLocalDateTime(String dateTimeString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            return  LocalDateTime.parse(dateTimeString, formatter);

        } catch (DateTimeParseException e) {
            System.out.println("Invalid date-time format: " + e.getMessage());
        }
        return null;
    }

}
