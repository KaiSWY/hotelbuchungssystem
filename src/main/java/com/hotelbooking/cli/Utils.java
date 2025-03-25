package com.hotelbooking.cli;

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
        }
        catch (NumberFormatException e)
        {
            System.out.println("Invalid number format: " + e);
            return -1;
        }
    }

    //method to create local date time with string
    public static LocalDateTime createDateTime(String dateTime)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");

        try
        {
            return LocalDateTime.parse(dateTime, formatter);
        }
        catch (DateTimeParseException e)
        {
            System.out.println("Invalid date format: " + e);
            return null;
        }
    }
}
