package com.hotelbooking.cli.interaction_classes;

import com.hotelbooking.HibernateSessionFactoryBuilder;
import com.hotelbooking.cli.Utils;
import com.hotelbooking.cli.enums.MainCommands;
import com.hotelbooking.cli.enums.SubCommands;
import com.hotelbooking.model.*;
import com.hotelbooking.repository.*;
import com.hotelbooking.service.BookingService;
import com.hotelbooking.service.ParkingSpotBookingService;
import com.hotelbooking.service.RoomBookingService;
import com.hotelbooking.service.analysers.AnalyseResult;
import com.hotelbooking.service.analysers.implementations.RoomBookingAnalyser;
import org.hibernate.SessionFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.hotelbooking.cli.HotelBookingCLI.extractParameters;

public class RoomBookingInteraction implements IBasicMethods
{
    //parameters
    private String mainCommand;
    private String[] subValues;
    private String[] subCommands;

    //initialize repositories
    private IRepository<Room, Integer> roomRepository;
    private UserRepository userRepository;
    private IRepository<Room_User, Integer> roomUserRepository;
    private IRepository<ParkingSpot, Integer> parkingSpotRepository;
    private IRepository<ParkingSpot_User, Integer> parkingSpotUserRepository;

    private BookingService<ParkingSpot_User> parkingSpotBookingService;

    public RoomBookingInteraction()
    {
    }

    public RoomBookingInteraction(String mainCommand, String[] subValues, String[] subCommands)
    {
        this.mainCommand = mainCommand;
        this.subValues = subValues;
        this.subCommands = subCommands;
    }

    @Override
    public void create()
    {
        if (SubCommands.commandsPartOfEnum(subCommands) && MainCommands.checkSubCommandsMatchMainCommandGroup(this.mainCommand, subCommands))
        {
            try (SessionFactory sessionFactory = getSessionFactory())
            {
                //get input data map
                Map<SubCommands, String> extractedParameters = extractParameters(subValues);

                this.roomRepository = new RoomRepository(sessionFactory);
                this.userRepository = new UserRepository(sessionFactory);
                this.roomUserRepository = new RoomUserRepository(sessionFactory);
                this.parkingSpotRepository = new ParkingSpotRepository(sessionFactory);
                this.parkingSpotUserRepository = new ParkingSpotUserRepository(sessionFactory);

                BookingService<ParkingSpot_User> parkingSpotBookingService = new ParkingSpotBookingService(
                        parkingSpotUserRepository,
                        userRepository,
                        parkingSpotRepository
                );
                BookingService<Room_User> roomBookingService = new RoomBookingService(
                        roomUserRepository,
                        userRepository,
                        roomRepository,
                        parkingSpotBookingService
                );

                Optional<User> selectedUser = userRepository.getUserByEmail(extractedParameters.get(SubCommands.MAIL));
                if(selectedUser.isEmpty())
                {
                    System.out.println("User not found!");
                    return;
                }

                Booking roomBooking = new Room_User (
                        roomRepository.getById(Integer.parseInt(extractedParameters.get(SubCommands.ROOM_NUMBER))),
                        selectedUser.get(),
                        Utils.createDateTime(extractedParameters.get(SubCommands.START_DATE)),
                        Utils.createDateTime(extractedParameters.get(SubCommands.END_DATE))
                );

                roomBookingService.book(roomBooking);

                System.out.println("Room booked!");
            }
        }
    }

    @Override
    public void get()
    {
        if (SubCommands.commandsPartOfEnum(subCommands) && MainCommands.checkSubCommandsMatchMainCommandGroup(this.mainCommand, subCommands))
        {
            try (SessionFactory sessionFactory = getSessionFactory())
            {
                //get input data map
                Map<SubCommands, String> extractedParameters = extractParameters(subValues);

                this.roomUserRepository = new RoomUserRepository(sessionFactory);
                this.userRepository = new UserRepository(sessionFactory);
                this.roomRepository = new RoomRepository(sessionFactory);
                this.parkingSpotRepository = new ParkingSpotRepository(sessionFactory);
                this.parkingSpotUserRepository = new ParkingSpotUserRepository(sessionFactory);

                this.parkingSpotBookingService = new ParkingSpotBookingService(
                        parkingSpotUserRepository,
                        userRepository,
                        parkingSpotRepository
                );

                RoomBookingService roomBookingService = new RoomBookingService(
                        roomUserRepository,
                        userRepository,
                        roomRepository,
                        parkingSpotBookingService
                );

                //check if to get bookings by id or timespan
                if(!Arrays.asList(subCommands).contains(SubCommands.END_DATE.name()))
                {
                    Room_User roomUser = roomBookingService.getById(Utils.createNumber(extractedParameters.get(SubCommands.ID)));
                    System.out.println(roomUser.toString());
                }
                else
                {
                    List<Room_User> roomUserByTimespan = roomBookingService.getBookingsByTimeSpan(
                            Utils.createDateTime(extractedParameters.get(SubCommands.START_DATE)),
                            Utils.createDateTime(extractedParameters.get(SubCommands.END_DATE))
                    );

                    //get bookings by timespan
                    for (Room_User currentRoomUser : roomUserByTimespan)
                    {
                              System.out.println(currentRoomUser.toString());
                    }
                }
            }
        }
    }

    @Override
    public void delete()
    {
        if (SubCommands.commandsPartOfEnum(subCommands) && MainCommands.checkSubCommandsMatchMainCommandGroup(this.mainCommand, subCommands))
        {
            try (SessionFactory sessionFactory = getSessionFactory())
            {
                //get input data map
                Map<SubCommands, String> extractedParameters = extractParameters(subValues);

                this.roomUserRepository = new RoomUserRepository(sessionFactory);
                this.userRepository = new UserRepository(sessionFactory);
                this.roomRepository = new RoomRepository(sessionFactory);
                this.parkingSpotRepository = new ParkingSpotRepository(sessionFactory);
                this.parkingSpotUserRepository = new ParkingSpotUserRepository(sessionFactory);

                this.parkingSpotBookingService = new ParkingSpotBookingService(
                        parkingSpotUserRepository,
                        userRepository,
                        parkingSpotRepository
                );

                RoomBookingService roomBookingService = new RoomBookingService(
                        roomUserRepository,
                        userRepository,
                        roomRepository,
                        parkingSpotBookingService
                );

                roomBookingService.cancel(Utils.createNumber(extractedParameters.get(SubCommands.ID)));

                System.out.println("Room booking deleted!");
            }
        }
    }

    @Override
    public void analytics()
    {
        if (SubCommands.commandsPartOfEnum(subCommands) && MainCommands.checkSubCommandsMatchMainCommandGroup(this.mainCommand, subCommands))
        {
            try (SessionFactory sessionFactory = getSessionFactory())
            {
                //get input data map
                Map<SubCommands, String> extractedParameters = extractParameters(subValues);

                this.roomRepository = new RoomRepository(sessionFactory);
                this.roomUserRepository = new RoomUserRepository(sessionFactory);

                RoomBookingAnalyser roomBookingAnalyser = new RoomBookingAnalyser(
                        this.roomRepository,
                        this.roomUserRepository
                );

                Room room = this.roomRepository.getById(Utils.createNumber(extractedParameters.get(SubCommands.ROOM_NUMBER)));

                AnalyseResult result;
                if (!Arrays.asList(subCommands).contains(SubCommands.END_DATE.name()))
                {
                    //analyse by id
                    result = roomBookingAnalyser.analyse(room);
                }
                else
                {
                    //analyse by timespan
                    result = roomBookingAnalyser.analyseBetweenDate(
                            room,
                            Utils.createDateTime(extractedParameters.get(SubCommands.START_DATE)),
                            Utils.createDateTime(extractedParameters.get(SubCommands.END_DATE))
                    );
                }
                System.out.println("Room booking analysis result:\n" + result.toString());
            }
        }
    }

    private SessionFactory getSessionFactory()
    {
        HibernateSessionFactoryBuilder factoryBuilder = new HibernateSessionFactoryBuilder();
        return factoryBuilder.createSessionFactory();
    }
}
