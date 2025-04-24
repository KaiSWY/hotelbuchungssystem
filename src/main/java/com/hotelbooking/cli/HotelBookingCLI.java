package com.hotelbooking.cli;

import com.hotelbooking.cli.enums.MainCommands;
import com.hotelbooking.cli.enums.SubCommands;
import com.hotelbooking.cli.interaction_classes.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HotelBookingCLI
{
    //args
    private String[] args;

    //interaction classes
    private UserInteraction userInteraction;
    private RoomBookingInteraction roomBookingInteraction;
    private ParkingSpotInteraction parkingSpotInteraction;
    private RestaurantTableInteraction restaurantTableInteraction;
    private ActivityBookingInteraction activityBookingInteraction;

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
            String[] subValues = this.getStructuredCommands()[1];
            String[] totalSubCommands = this.getStructuredCommands()[2];

            //find corresponding matching command
            MainCommands matchingCommand = MainCommands.findCommandsByValue(mainCommand);

            //initialize all interaction classes
            this.userInteraction = new UserInteraction(mainCommand, subValues, totalSubCommands);
            this.roomBookingInteraction = new RoomBookingInteraction(mainCommand, subValues, totalSubCommands);
            this.parkingSpotInteraction = new ParkingSpotInteraction(mainCommand, subValues, totalSubCommands);
            this.restaurantTableInteraction = new RestaurantTableInteraction(mainCommand, subValues, totalSubCommands);
            this.activityBookingInteraction = new ActivityBookingInteraction(mainCommand, subValues, totalSubCommands);

            if (matchingCommand != null)
            {
                switch (MainCommands.findCommandsByValue(mainCommand))
                {
                    case CREATE_USER:
                        this.userInteraction.create();
                        break;
                    case GET_USER:
                        this.userInteraction.get();
                        break;
                    case DELETE_USER:
                        this.userInteraction.delete();
                        break;
                    case CREATE_ROOM_BOOKING:
                        this.roomBookingInteraction.create();
                        break;
                    case GET_ROOM_BOOKING:
                        this.roomBookingInteraction.get();
                        break;
                    case DELETE_ROOM_BOOKING:
                        this.roomBookingInteraction.delete();
                        break;
                    case GET_ROOM_BOOKING_ANALYSIS:
                        this.roomBookingInteraction.analytics();
                        break;
                    case BOOK_PARKING_SPOT:
                        this.parkingSpotInteraction.create();
                        break;
                    case GET_BOOKED_PARKING_SPOT:
                        this.parkingSpotInteraction.get();
                        break;
                    case DELETE_BOOKED_PARKING_SPOT:
                        this.parkingSpotInteraction.delete();
                        break;
                    case GET_BOOKED_PARKING_SPOT_ANALYSIS:
                        this.parkingSpotInteraction.analytics();
                        break;
                    case BOOK_RESTAURANT_TABLE:
                        this.restaurantTableInteraction.create();
                        break;
                    case GET_BOOKED_RESTAURANT_TABLE:
                        this.restaurantTableInteraction.get();
                        break;
                    case DELETE_BOOKED_RESTAURANT_TABLE:
                        this.restaurantTableInteraction.delete();
                        break;
                    case GET_BOOKED_RESTAURANT_TABLE_ANALYSIS:
                        this.restaurantTableInteraction.analytics();
                        break;
                    case BOOK_ACTIVITY:
                        this.activityBookingInteraction.create();
                        break;
                    case GET_BOOKED_ACTIVITY:
                        this.activityBookingInteraction.get();
                        break;
                    case DELETE_BOOKED_ACTIVITY:
                        this.activityBookingInteraction.delete();
                        break;
                    case GET_BOOKED_ACTIVITY_ANALYSIS:
                        this.activityBookingInteraction.analytics();
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
        return MainCommands.commandPartOfEnums(mainCommandValue);
    }

    //method to get structured commands
    private String[][] getStructuredCommands()
    {
        //create total sub commands string array
        String[] totalSubCommandsArray = Arrays.stream(args)
                .filter(arg -> arg.startsWith("--"))
                .toArray(String[]::new);

        //return new string array
        return new String[][]{
                {args[0]},
                Arrays.copyOfRange(args, 1, args.length),
                totalSubCommandsArray
        };
    }

    //method to extract input values
    public static Map<SubCommands, String> extractParameters(String[] args)
    {
        Map<SubCommands, String> parameters = new HashMap<>();

        for (int iIndex = 0; iIndex < args.length - 1; iIndex++)
        {
            if (args[iIndex].startsWith("--"))
            {
                SubCommands key = SubCommands.findCommandsByValue(args[iIndex]);
                if (key != null)
                {
                    parameters.put(key, args[iIndex + 1]);
                }
            }
        }
        return parameters;
    }
}