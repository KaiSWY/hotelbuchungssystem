package com.hotelbooking.cli;

import com.hotelbooking.HibernateUtil;
import com.hotelbooking.cli.enums.CommandMapping;
import com.hotelbooking.cli.enums.MainCommands;
import com.hotelbooking.cli.enums.SubCommands;
import com.hotelbooking.controller.*;
import com.hotelbooking.model.Activity;
import com.hotelbooking.model.Activity_User;
import com.hotelbooking.model.Room;
import com.hotelbooking.model.User;
import com.hotelbooking.repository.ActivityRepository;
import com.hotelbooking.repository.ActivityUserRepository;
import com.hotelbooking.repository.IRepository;
import com.hotelbooking.repository.UserRepository;
import org.hibernate.SessionFactory;

import java.util.Arrays;

public class HotelBookingCLI
{
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
            String[] subValues = this.getStructuredCommands()[1];
            String[] totalSubCommands = this.getStructuredCommands()[2];

            //find corresponding matching command
            MainCommands matchingCommand = MainCommands.findCommandsByValue(mainCommand);

            if (matchingCommand != null)
            {
                switch (MainCommands.findCommandsByValue(mainCommand))
                {
                    case CREATE_USER:
                        this.createUserInteraction(matchingCommand, subValues, totalSubCommands);
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
        //create total sub commands string array
        String[] totalSubCommandsArray = Arrays.stream(args)
                .filter(arg -> arg.startsWith("--"))
                .toArray(String[]::new);

        //return new string array
        return new String[][] {
                {args[0]},
                Arrays.copyOfRange(args, 1, args.length),
                totalSubCommandsArray
        };
    }

    //method for create user interaction
    private void createUserInteraction(MainCommands mainCommand, String[] subValues, String[] subCommands)
    {
        if (SubCommands.commandsPartOfEnum(subCommands) && CommandMapping.subCommandsPartOfMainCommands(mainCommand, subCommands))
        {
            try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory())
            {
                IRepository<User, Integer> userRepository = new UserRepository(sessionFactory);
                IRepository<Activity, Integer> activityRepository = new ActivityRepository(sessionFactory);
                IRepository<Activity_User, Integer> activityUserRepository = new ActivityUserRepository(sessionFactory);



            }
            HibernateUtil.shutdown();

            System.out.println("success");
        }
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
