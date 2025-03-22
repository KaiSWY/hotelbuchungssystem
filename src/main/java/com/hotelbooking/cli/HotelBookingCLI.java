package com.hotelbooking.cli;

import com.hotelbooking.cli.enums.MainCommands;
import com.hotelbooking.controller.*;

import java.util.Arrays;

public class HotelBookingCLI {
    private String[] args;

    //controller
    private UserRegistrationController userRegistrationController;
    private RoomBookingController roomBookingController;
    private RestaurantTableBookingController restaurantTableBookingController;
    private ParkingSpotBookingController parkingSpotBookingController;
    private ActivityBookingController activityBookingController;

    public HotelBookingCLI()
    {
    }

    public HotelBookingCLI(String[] args)
    {
        this.args = args;
    }

    //method to execute action
    public void execute()
    {
        //check if input is valid
        if (checkInputValid())
        {
            //check case with switch case
            String mainCommand = this.getStructuredCommands()[0][0];
            String[] totalSubCommands = this.getStructuredCommands()[1];

            //find corresponding matching command
            MainCommands matchingCommand = MainCommands.findCommandsByValue(mainCommand);

            if (matchingCommand != null)
            {
                switch (MainCommands.findCommandsByValue(mainCommand))
                {
                    case CREATE_USER:
                        this.createUserInteraction(totalSubCommands);
                        break;
                    case CREATE_ROOM_BOOKING:
                        this.createRoomBookingInteraction(totalSubCommands);
                        break;
                    case BOOK_PARKING_SPOT:
                        this.createParkingSpotInteraction(totalSubCommands);
                        break;
                    case BOOK_RESTAURANT_TABLE:
                        this.createRestaurantTableInteraction(totalSubCommands);
                        break;
                    case BOOK_ACTIVITY:
                        this.createActivityInteraction(totalSubCommands);
                        break;
                }
            }
            else
            {
                System.out.println("Invalid command!");
            }
        }
        else
        {
            System.out.println("Invalid input");
        }
    }

    //method to check if input is valid
    private boolean checkInputValid()
    {
        String mainCommandValue = args[0];
        if (MainCommands.commandPartOfEnums(mainCommandValue) && (args.length >= 3))
        {
            return true;
        }

        return false;
    }

    //method to get structured commands
    private String[][] getStructuredCommands()
    {
        return new String[][]{
                {args[0]},
                Arrays.copyOfRange(args, 1, args.length)
        };
    }

    //method for create user interaction
    private void createUserInteraction(String[] subCommands)
    {
        System.out.println("create user");


    }

    //method for create room booking interaction
    private void createRoomBookingInteraction(String[] subCommands)
    {

    }

    //method for create parking sport interaction
    private void createParkingSpotInteraction(String[] subCommands)
    {

    }

    //method for create restaurant table interaction
    private void createRestaurantTableInteraction(String[] subCommands)
    {

    }

    //method for create activity interaction
    private void createActivityInteraction(String[] subCommands)
    {

    }
}
