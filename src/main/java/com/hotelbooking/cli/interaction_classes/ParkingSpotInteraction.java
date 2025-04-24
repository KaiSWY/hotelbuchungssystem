package com.hotelbooking.cli.interaction_classes;

import com.hotelbooking.HibernateSessionFactoryFactory;
import com.hotelbooking.cli.Utils;
import com.hotelbooking.cli.enums.MainCommands;
import com.hotelbooking.cli.enums.SubCommands;
import com.hotelbooking.model.*;
import com.hotelbooking.repository.IRepository;
import com.hotelbooking.repository.ParkingSpotRepository;
import com.hotelbooking.repository.ParkingSpotUserRepository;
import com.hotelbooking.repository.UserRepository;
import com.hotelbooking.service.BookingService;
import com.hotelbooking.service.ParkingSpotBookingService;
import com.hotelbooking.service.analysers.AnalyseResult;
import com.hotelbooking.service.analysers.implementations.ParkingSpotBookingAnalyser;
import org.hibernate.SessionFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.hotelbooking.cli.HotelBookingCLI.extractParameters;

public class ParkingSpotInteraction implements IBasicMethods
{
    //parameters
    private String mainCommand;
    private String[] subValues;
    private String[] subCommands;

    //initialize repositories
    private IRepository<ParkingSpot_User, Integer> parkingSpotUserRepository;
    private IRepository<ParkingSpot, Integer> parkingSpotRepository;
    private UserRepository userRepository;

    private BookingService<ParkingSpot_User> parkingSpotBookingService;

    public ParkingSpotInteraction()
    {
    }

    public ParkingSpotInteraction(String mainCommand, String[] subValues, String[] subCommands)
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

                this.parkingSpotUserRepository = new ParkingSpotUserRepository(sessionFactory);
                this.parkingSpotRepository = new ParkingSpotRepository(sessionFactory);
                this.userRepository = new UserRepository(sessionFactory);

                this.parkingSpotBookingService = new ParkingSpotBookingService(
                        parkingSpotUserRepository,
                        userRepository,
                        parkingSpotRepository
                );

                //get user by mail
                Optional<User> selectedUser = userRepository.getUserByEmail(extractedParameters.get(SubCommands.MAIL));
                if(selectedUser.isEmpty())
                {
                    System.out.println("User not found!");
                    return;
                }

                //get parking spot by id
                ParkingSpot selectedParkingSpot = parkingSpotRepository.getById(Utils.createNumber(extractedParameters.get(SubCommands.ID)));

                Booking parkingSpotBooking = new ParkingSpot_User (
                        selectedParkingSpot,
                        selectedUser.get(),
                        Utils.createDateTime(extractedParameters.get(SubCommands.START_DATE)),
                        Utils.createDateTime(extractedParameters.get(SubCommands.END_DATE))
                );

                parkingSpotBookingService.book(parkingSpotBooking);

                System.out.println("Parking spot booked!");
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

                this.parkingSpotUserRepository = new ParkingSpotUserRepository(sessionFactory);
                this.userRepository = new UserRepository(sessionFactory);
                this.parkingSpotRepository = new ParkingSpotRepository(sessionFactory);

                this.parkingSpotBookingService = new ParkingSpotBookingService(
                        parkingSpotUserRepository,
                        userRepository,
                        parkingSpotRepository
                );

                //check if to get bookings by id or timespan
                if(!Arrays.asList(subCommands).contains(SubCommands.END_DATE.name()))
                {
                    ParkingSpot_User parkingSpotUser = parkingSpotBookingService.getById(Utils.createNumber(extractedParameters.get(SubCommands.ID)));
                    //get bookings by id
                    System.out.println(parkingSpotUser.toString());
                }
                else
                {
                    List<ParkingSpot_User> parkingSpotUserList = parkingSpotBookingService.getBookingsByTimeSpan(
                            Utils.createDateTime(extractedParameters.get(SubCommands.START_DATE)),
                            Utils.createDateTime(extractedParameters.get(SubCommands.END_DATE))
                    );

                    //get bookings by timespan
                    for (ParkingSpot_User currentParkingSpotUser : parkingSpotUserList)
                    {
                        System.out.println(currentParkingSpotUser.toString());
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

                this.parkingSpotUserRepository = new ParkingSpotUserRepository(sessionFactory);
                this.userRepository = new UserRepository(sessionFactory);
                this.parkingSpotRepository = new ParkingSpotRepository(sessionFactory);

                this.parkingSpotBookingService = new ParkingSpotBookingService(
                        parkingSpotUserRepository,
                        userRepository,
                        parkingSpotRepository
                );

                parkingSpotBookingService.cancel(Utils.createNumber(extractedParameters.get(SubCommands.ID)));

                System.out.println("Parking spot booking deleted!");
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

                this.parkingSpotRepository = new ParkingSpotRepository(sessionFactory);
                this.parkingSpotUserRepository = new ParkingSpotUserRepository(sessionFactory);

                ParkingSpotBookingAnalyser parkingSpotBookingAnalyser = new ParkingSpotBookingAnalyser(
                        parkingSpotRepository,
                        parkingSpotUserRepository
                );

                ParkingSpot parkingSpot = this.parkingSpotRepository.getById(Utils.createNumber(extractedParameters.get(SubCommands.ID)));

                //check if to get analysis by id or timespan
                AnalyseResult result;
                if (!Arrays.asList(subCommands).contains(SubCommands.END_DATE.name()))
                {
                    //analyse by id
                    result = parkingSpotBookingAnalyser.analyse(parkingSpot);
                }
                else
                {
                    //analyse by timestamp
                    result = parkingSpotBookingAnalyser.analyseBetweenDate(
                            parkingSpot,
                            Utils.createDateTime(extractedParameters.get(SubCommands.START_DATE)),
                            Utils.createDateTime(extractedParameters.get(SubCommands.END_DATE))
                    );
                }
                System.out.println("Parking spot booking analysis result:\n" + result.toString());
            }
        }
    }

    private SessionFactory getSessionFactory()
    {
        HibernateSessionFactoryFactory factoryBuilder = new HibernateSessionFactoryFactory();
        return factoryBuilder.createSessionFactory();
    }
}
