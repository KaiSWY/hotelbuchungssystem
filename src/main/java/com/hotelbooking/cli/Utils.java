package com.hotelbooking.cli;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utils
{
    //method to turn string into int
    public static int createNumber(String number)
    {
        try
        {
            return Integer.parseInt(number);
        } catch (NumberFormatException e)
        {
            return -1;
        }
    }

    //method to create local date time with string
    public static LocalDateTime createDateTime(String dateTime)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        try
        {
            LocalDate date = LocalDate.parse(dateTime, formatter);
            return date.atStartOfDay();
        } catch (DateTimeParseException dateTimeParseException)
        {
            System.out.println("Invalid Date: " + dateTimeParseException.getMessage());
            return null;
        }
    }
}
