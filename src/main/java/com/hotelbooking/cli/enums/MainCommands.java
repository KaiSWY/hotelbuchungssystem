package com.hotelbooking.cli.enums;

import java.util.Arrays;

public enum MainCommands
{
    CREATE_USER("--createUser"),
    GET_USER("--getUser"),
    DELETE_USER("--deleteUser"),
    GET_USER_ANALYSIS("--getUserAnalysis"),
    CREATE_ROOM_BOOKING("--createRoomBooking"),
    GET_ROOM_BOOKING("--getRoomBooking"),
    DELETE_ROOM_BOOKING("--deleteRoomBooking"),
    GET_ROOM_BOOKING_ANALYSIS("--getRoomBookingAnalysis"),
    BOOK_PARKING_SPOT("--bookParkingSpot"),
    GET_BOOKED_PARKING_SPOT("--getBookedParkingSpot"),
    DELETE_BOOKED_PARKING_SPOT("--deleteBookedParkingSpot"),
    GET_BOOKED_PARKING_SPOT_ANALYSIS("--getBookedParkingSpotAnalysis"),
    BOOK_RESTAURANT_TABLE("--bookRestaurantTable"),
    GET_BOOKED_RESTAURANT_TABLE("--getBookedRestaurantTable"),
    DELETE_BOOKED_RESTAURANT_TABLE("--deleteBookedRestaurantTable"),
    GET_BOOKED_RESTAURANT_TABLE_ANALYSIS("--getBookedRestaurantTableAnalysis"),
    BOOK_ACTIVITY("--bookActivity"),
    GET_BOOKED_ACTIVITY("--getBookedActivity"),
    DELETE_BOOKED_ACTIVITY("--deleteBookedActivity"),
    GET_BOOKED_ACTIVITY_ANALYSIS("--getBookedActivityAnalysis");

    private String command;

    MainCommands(String command)
    {
        this.command = command;
    }

    public String getCommand()
    {
        return command;
    }

    //method to check if command is part of enums
    public static boolean commandPartOfEnums(String mainCommand)
    {
        for (MainCommands allCommands : MainCommands.values())
        {
            if (allCommands.getCommand().equalsIgnoreCase(mainCommand))
            {
                return true;
            }
        }

        return false;
    }

    //method to find commands by value
    public static MainCommands findCommandsByValue(String commandValue)
    {
        return Arrays.stream(MainCommands.values())
                .filter(command -> command.getCommand().equalsIgnoreCase(commandValue))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString()
    {
        return "MainCommands{" +
                "command='" + command + '\'' +
                '}';
    }
}
