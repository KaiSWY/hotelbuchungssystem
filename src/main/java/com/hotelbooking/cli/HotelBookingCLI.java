package com.hotelbooking.cli;

import com.hotelbooking.HibernateUtil;
import com.hotelbooking.cli.enums.MainCommands;
import com.hotelbooking.cli.enums.SubCommands;
import com.hotelbooking.model.*;
import com.hotelbooking.repository.*;
import com.hotelbooking.service.BookingService;
import com.hotelbooking.service.ParkingSpotBookingService;
import com.hotelbooking.service.RoomBookingService;
import com.hotelbooking.service.UserRegistrationService;
import org.hibernate.SessionFactory;

import java.util.*;

public class HotelBookingCLI
{
    private String[] args;

    private String mainCommand;

    private IRepository<User, Integer> userRepository;
    private UserRegistrationService userRegistrationService;

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
            this.mainCommand = this.getStructuredCommands()[0][0];
            String[] subValues = this.getStructuredCommands()[1];
            String[] totalSubCommands = this.getStructuredCommands()[2];

            //find corresponding matching command
            MainCommands matchingCommand = MainCommands.findCommandsByValue(mainCommand);

            if (matchingCommand != null)
            {
                switch (MainCommands.findCommandsByValue(mainCommand))
                {
                    case CREATE_USER:
                        this.createUserInteraction(subValues, totalSubCommands);
                        break;
                    case GET_USER:
                        this.getUserInformationInteraction(subValues, totalSubCommands);
                        break;
                    case DELETE_USER:
                        this.deleteUserInteraction(subValues, totalSubCommands);
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

    //method to extract input values
    private static Map<SubCommands, String> extractParameters(String[] args) {
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

    //method for create user interaction
    private void createUserInteraction(String[] subValues, String[] subCommands)
    {
        if (SubCommands.commandsPartOfEnum(subCommands) && MainCommands.checkSubCommandsMatchMainCommandGroup(this.mainCommand, subCommands))
        {
            try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory())
            {
                Map<SubCommands, String> extractedParameters = extractParameters(subValues);

                //create new registration
                User newUser = new User(
                        extractedParameters.get(SubCommands.FIRST_NAME),
                        extractedParameters.get(SubCommands.LAST_NAME),
                        extractedParameters.get(SubCommands.MAIL),
                        extractedParameters.get(SubCommands.COUNTRY),
                        Utils.createNumber(extractedParameters.get(SubCommands.ZIP_CODE)),
                        extractedParameters.get(SubCommands.CITY),
                        extractedParameters.get(SubCommands.STREET),
                        Utils.createNumber(extractedParameters.get(SubCommands.HOUSE_NUMBER))
                );

                this.userRepository = new UserRepository(sessionFactory);
                this.userRegistrationService = new UserRegistrationService(userRepository);
                this.userRegistrationService.create(newUser);

                System.out.println("User created!");
            }
            HibernateUtil.shutdown();
        }
    }

    //method to get user information
    private void getUserInformationInteraction(String[] subValues, String[] subCommands)
    {
        if (SubCommands.commandsPartOfEnum(subCommands) && MainCommands.checkSubCommandsMatchMainCommandGroup(this.mainCommand, subCommands))
        {
            try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory())
            {
                //get input data map
                Map<SubCommands, String> extractedParameters = extractParameters(subValues);

                this.userRepository = new UserRepository(sessionFactory);
                this.userRegistrationService = new UserRegistrationService(userRepository);

                User receivedUser = this.userRegistrationService.getById(Integer.parseInt(extractedParameters.get(SubCommands.ID)));

                //set information output
                System.out.println("User Information: " + receivedUser.toString());
            }
            HibernateUtil.shutdown();
        }
    }

    //method to delete user
    private void deleteUserInteraction(String[] subValues, String[] subCommands)
    {
        if (SubCommands.commandsPartOfEnum(subCommands) && MainCommands.checkSubCommandsMatchMainCommandGroup(this.mainCommand, subCommands))
        {
            try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory())
            {
                //get input data map
                Map<SubCommands, String> extractedParameters = extractParameters(subValues);

                this.userRepository = new UserRepository(sessionFactory);
                this.userRegistrationService = new UserRegistrationService(userRepository);

                this.userRegistrationService.delete(Integer.parseInt(extractedParameters.get(SubCommands.ID)));

                //set information output
                System.out.println("User deleted!");
            }
            HibernateUtil.shutdown();
        }
    }

    //method for create room booking interaction
    private void createRoomBookingInteraction(String[] subCommands)
    {
        if (SubCommands.commandsPartOfEnum(subCommands) && MainCommands.checkSubCommandsMatchMainCommandGroup(this.mainCommand, subCommands))
        {
            try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory())
            {
                //get input data map
                Map<SubCommands, String> extractedParameters = extractParameters(subCommands);

                IRepository<Room, Integer> roomRepository = new RoomRepository(sessionFactory);
                UserRepository userRepository = new UserRepository(sessionFactory);
                IRepository<Room_User, Integer> roomUserRepository = new RoomUserRepository(sessionFactory);
                IRepository<ParkingSpot, Integer> parkingSpotRepository = new ParkingSpotRepository(sessionFactory);
                IRepository<ParkingSpot_User, Integer> parkingSpotUserRepository = new ParkingSpotUserRepository(sessionFactory);

                BookingService<ParkingSpot_User> parkingSpotBookingService = new ParkingSpotBookingService(
                        parkingSpotUserRepository,
                        userRepository,
                        parkingSpotRepository);
                BookingService<Room_User> roomBookingService = new RoomBookingService(
                        roomUserRepository,
                        userRepository,
                        roomRepository,
                        parkingSpotBookingService);

                Optional<User> selectedUser = userRepository.getUserByEmail(extractedParameters.get(SubCommands.MAIL));
                if(selectedUser.isEmpty())
                {
                    System.out.println("User not found!");
                    return;
                }

                Booking roomBooking = new Room_User(
                        roomRepository.getById(Integer.parseInt(extractedParameters.get(SubCommands.ROOM_NUMBER))),
                        selectedUser.get(),
                        Utils.createDateTime(extractedParameters.get(SubCommands.START_DATE)),
                        Utils.createDateTime(extractedParameters.get(SubCommands.END_DATE))
                );

                roomBookingService.book(roomBooking);

                System.out.println("Room booked!");
            }
            HibernateUtil.shutdown();
        }
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
