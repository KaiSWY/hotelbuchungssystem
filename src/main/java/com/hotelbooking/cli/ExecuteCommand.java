package com.hotelbooking.cli;

import com.hotelbooking.model.ParkingSpot;
import com.hotelbooking.model.RestaurantTable;
import com.hotelbooking.model.Room;
import com.hotelbooking.model.User;
import com.hotelbooking.service.ParkingSpotBookingService;
import com.hotelbooking.service.RestaurantTableBookingService;
import com.hotelbooking.service.RoomBookingService;
import com.hotelbooking.service.UserRegistrationService;

public class ExecuteCommand
{
    private String command;
    private String value;

    private IGetValuesInterface iGetValuesInterface;

    //service classes
    private UserRegistrationService userRegistrationService;
    private RoomBookingService roomBookingService;
    private ParkingSpotBookingService parkingSpotBookingService;
    private RestaurantTableBookingService restaurantTableBookingService;

    public ExecuteCommand()
    {
    }

    public ExecuteCommand(String command, String value)
    {
        this.command = command;
        this.value = value;
    }

    public ExecuteCommand(IGetValuesInterface iGetValuesInterface, UserRegistrationService userRegistrationService, RoomBookingService roomBookingService, ParkingSpotBookingService parkingSpotBookingService, RestaurantTableBookingService restaurantTableBookingService)
    {
        this.iGetValuesInterface = iGetValuesInterface;
        this.userRegistrationService = userRegistrationService;
        this.roomBookingService = roomBookingService;
        this.parkingSpotBookingService = parkingSpotBookingService;
        this.restaurantTableBookingService = restaurantTableBookingService;
    }

    public String getCommand()
    {
        return command;
    }

    public void setCommand(String command)
    {
        this.command = command;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    //method to run action
    public boolean runAction(String mainCommand, String[] actionArgs)
    {
        //try catch for error handling
        try
        {
            switch (mainCommand)
            {
                case "bookRoom":
                    this.roomBookingAction(actionArgs);
                    break;
                case "bookParkingSpot":
                    this.parkingSpotBookingAction(actionArgs);
                    break;
                case "bookRestaurantTable":
                    this.restaurantTableBookingAction(actionArgs);
                    break;
                default:
                    return false;
            }

            return true;
        }
        catch (Exception eRunAction)
        {
            return false;
        }
    }

    //user registration action method
    public User userRegistrationAction(String[] actionArgs)
    {
        System.out.println(actionArgs.toString());

        User newUser = new User();

        return newUser;
    }

    //room booking action method
    public Room roomBookingAction(String[] actionArgs)
    {
        //get different values
        String roomNumber = this.iGetValuesInterface.getValue(actionArgs);
        System.out.println(roomNumber);

        Room newRoom = new Room();

        return newRoom;
    }

    //parking spot booking service method
    public ParkingSpot parkingSpotBookingAction(String[] actionArgs)
    {
        System.out.println(actionArgs.toString());

        ParkingSpot newParkingSpot = new ParkingSpot();

        return newParkingSpot;
    }

    //restaurant table booking service method
    public RestaurantTable restaurantTableBookingAction(String[] actionArgs)
    {
        System.out.println(actionArgs.toString());

        RestaurantTable newRestaurantTable = new RestaurantTable();

        return newRestaurantTable;
    }
}