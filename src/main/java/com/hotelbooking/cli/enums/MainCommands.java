package com.hotelbooking.cli.enums;

import java.util.Arrays;
import java.util.List;

//xy.exe hauptbefehl --subbefehl1 value1 --subbefehl2 value2
public enum MainCommands
{
    CREATE_USER("createUser", List.of(
            SubCommands.FIRST_NAME,
            SubCommands.LAST_NAME)
    ),
    GET_USER("getUser", List.of(
            SubCommands.ID)
    ),
    DELETE_USER("deleteUser", List.of(
            SubCommands.ID)
    ),
    CREATE_ROOM_BOOKING("createRoomBooking", List.of(
            SubCommands.ROOM_NUMBER,
            SubCommands.MAIL,
            SubCommands.START_DATE,
            SubCommands.END_DATE)
    ),
    GET_ROOM_BOOKING("getRoomBooking", List.of(
            SubCommands.ID,
            SubCommands.START_DATE,
            SubCommands.END_DATE)
    ),
    DELETE_ROOM_BOOKING("deleteRoomBooking", List.of(
            SubCommands.ID)
    ),
    GET_ROOM_BOOKING_ANALYSIS("getRoomBookingAnalysis", List.of(
            SubCommands.ROOM_NUMBER,
            SubCommands.START_DATE,
            SubCommands.END_DATE)
    ),
    BOOK_PARKING_SPOT("bookParkingSpot", List.of(
            SubCommands.ID,
            SubCommands.MAIL,
            SubCommands.START_DATE,
            SubCommands.END_DATE)
    ),
    GET_BOOKED_PARKING_SPOT("getBookedParkingSpot", List.of(
            SubCommands.ID,
            SubCommands.START_DATE,
            SubCommands.END_DATE)
    ),
    DELETE_BOOKED_PARKING_SPOT("deleteBookedParkingSpot", List.of(
            SubCommands.ID)
    ),
    GET_BOOKED_PARKING_SPOT_ANALYSIS("getBookedParkingSpotAnalysis", List.of(
            SubCommands.ID,
            SubCommands.START_DATE,
            SubCommands.END_DATE)
    ),
    BOOK_RESTAURANT_TABLE("bookRestaurantTable", List.of(
            SubCommands.ID,
            SubCommands.MAIL,
            SubCommands.START_DATE,
            SubCommands.END_DATE)
    ),
    GET_BOOKED_RESTAURANT_TABLE("getBookedRestaurantTable", List.of(
            SubCommands.ID,
            SubCommands.START_DATE,
            SubCommands.END_DATE)
    ),
    DELETE_BOOKED_RESTAURANT_TABLE("deleteBookedRestaurantTable", List.of(
            SubCommands.ID)
    ),
    GET_BOOKED_RESTAURANT_TABLE_ANALYSIS("getBookedRestaurantTableAnalysis", List.of(
            SubCommands.ID,
            SubCommands.START_DATE,
            SubCommands.END_DATE)
    ),
    BOOK_ACTIVITY("bookActivity", List.of(
            SubCommands.ACTIVITY_ID,
            SubCommands.ID,
            SubCommands.START_DATE,
            SubCommands.END_DATE)
    ),
    GET_BOOKED_ACTIVITY("getBookedActivity", List.of(
            SubCommands.ID,
            SubCommands.START_DATE,
            SubCommands.END_DATE)
    ),
    DELETE_BOOKED_ACTIVITY("deleteBookedActivity", List.of(
            SubCommands.ID)
    ),
    GET_BOOKED_ACTIVITY_ANALYSIS("getBookedActivityAnalysis", List.of(
            SubCommands.ID,
            SubCommands.START_DATE,
            SubCommands.END_DATE)
    );

    private String command;
    private List<SubCommands> subCommands;

    MainCommands(String command, List<SubCommands> subCommands)
    {
        this.command = command;
        this.subCommands = subCommands;
    }

    public String getCommand()
    {
        return command;
    }

    public List<SubCommands> getSubCommands()
    {
        return subCommands;
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

    //method to check if sub commands match main command
    public static boolean checkSubCommandsMatchMainCommandGroup(String mainCommand, String[] subCommands)
    {
        MainCommands mainCommandEnum = findCommandsByValue(mainCommand);

        if (mainCommandEnum == null)
        {
            return false;
        }

        List<SubCommands> allowedSubCommands = mainCommandEnum.getSubCommands();

        for(String currentSubCommand : subCommands)
        {
            if (allowedSubCommands.contains(SubCommands.findCommandsByValue(currentSubCommand)))
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
