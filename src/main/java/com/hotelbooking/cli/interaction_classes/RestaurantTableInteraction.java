package com.hotelbooking.cli.interaction_classes;

import com.hotelbooking.HibernateSessionFactoryBuilder;
import com.hotelbooking.cli.Utils;
import com.hotelbooking.cli.enums.MainCommands;
import com.hotelbooking.cli.enums.SubCommands;
import com.hotelbooking.model.Booking;
import com.hotelbooking.model.RestaurantTable;
import com.hotelbooking.model.RestaurantTable_User;
import com.hotelbooking.model.User;
import com.hotelbooking.repository.IRepository;
import com.hotelbooking.repository.RestaurantTableRepository;
import com.hotelbooking.repository.RestaurantTableUserRepository;
import com.hotelbooking.repository.UserRepository;
import com.hotelbooking.service.Bookable;
import com.hotelbooking.service.BookingService;
import com.hotelbooking.service.RestaurantTableBookingService;
import com.hotelbooking.service.analysers.AnalyseResult;
import com.hotelbooking.service.analysers.IBookingAnalyser;
import com.hotelbooking.service.analysers.implementations.RestaurantTableBookingAnalyser;
import org.hibernate.SessionFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.hotelbooking.cli.HotelBookingCLI.extractParameters;

public class RestaurantTableInteraction implements IBasicMethods
{
    //parameters
    private String mainCommand;
    private String[] subValues;
    private String[] subCommands;

    //initialize repositories
    private IRepository<RestaurantTable_User, Integer> restaurantTableUserRepository;
    private IRepository<RestaurantTable, Integer> restaurantTableRepository;
    private UserRepository userRepository;

    private Bookable<RestaurantTable_User> restaurantTableBookingService;
    private IBookingAnalyser<RestaurantTable> restaurantTableBookingAnalyser;

    public RestaurantTableInteraction()
    {
    }

    public RestaurantTableInteraction(String mainCommand, String[] subValues, String[] subCommands)
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

                this.restaurantTableUserRepository = new RestaurantTableUserRepository(sessionFactory);
                this.restaurantTableRepository = new RestaurantTableRepository(sessionFactory);
                this.userRepository = new UserRepository(sessionFactory);

                this.restaurantTableBookingService = new RestaurantTableBookingService(
                        restaurantTableUserRepository,
                        userRepository,
                        restaurantTableRepository
                );

                //get user by mail
                Optional<User> selectedUser = this.userRepository.getUserByEmail(extractedParameters.get(SubCommands.MAIL));
                if (selectedUser.isEmpty())
                {
                    System.out.println("User not found!");
                    return;
                }

                //get restaurant table
                RestaurantTable selectedRestaurantTable = this.restaurantTableRepository.getById(Utils.createNumber(extractedParameters.get(SubCommands.ID)));

                Booking restaurantTableBooking = new RestaurantTable_User(
                        selectedRestaurantTable,
                        selectedUser.get(),
                        Utils.createDateTime(extractedParameters.get(SubCommands.START_DATE)),
                        Utils.createDateTime(extractedParameters.get(SubCommands.END_DATE))
                );

                this.restaurantTableBookingService.book(restaurantTableBooking);

                System.out.println("Restaurant table booked!");
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

                this.restaurantTableUserRepository = new RestaurantTableUserRepository(sessionFactory);
                this.restaurantTableRepository = new RestaurantTableRepository(sessionFactory);
                this.userRepository = new UserRepository(sessionFactory);

                BookingService<RestaurantTable_User> restaurantTableBookingService = new RestaurantTableBookingService(
                        restaurantTableUserRepository,
                        userRepository,
                        restaurantTableRepository
                );

                //check if to get bookings by id or timespan
                if (!Arrays.asList(subCommands).contains(SubCommands.END_DATE.name()))
                {
                    RestaurantTable_User restaurantTableUser = restaurantTableBookingService.getById(Utils.createNumber(extractedParameters.get(SubCommands.ID)));

                    //get bookings by id
                    System.out.println(restaurantTableUser.toString());
                }
                else
                {
                    List<RestaurantTable_User> restaurantTableUserList = restaurantTableBookingService.getBookingsByTimeSpan(
                            Utils.createDateTime(extractedParameters.get(SubCommands.START_DATE)),
                            Utils.createDateTime(extractedParameters.get(SubCommands.END_DATE))
                    );

                    //get bookings by timespan
                    for (RestaurantTable_User currentRestaurantTableUser : restaurantTableUserList)
                    {
                        System.out.println(currentRestaurantTableUser.toString());
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

                this.restaurantTableUserRepository = new RestaurantTableUserRepository(sessionFactory);
                this.restaurantTableRepository = new RestaurantTableRepository(sessionFactory);
                this.userRepository = new UserRepository(sessionFactory);

                this.restaurantTableBookingService = new RestaurantTableBookingService(
                        restaurantTableUserRepository,
                        userRepository,
                        restaurantTableRepository
                );

                this.restaurantTableBookingService.cancel(Utils.createNumber(extractedParameters.get(SubCommands.ID)));

                System.out.println("Restaurant table booking deleted!");
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

                this.restaurantTableRepository = new RestaurantTableRepository(sessionFactory);
                this.restaurantTableUserRepository = new RestaurantTableUserRepository(sessionFactory);

                restaurantTableBookingAnalyser = new RestaurantTableBookingAnalyser(
                        this.restaurantTableRepository,
                        this.restaurantTableUserRepository
                );

                RestaurantTable restaurantTable = this.restaurantTableRepository.getById(Utils.createNumber(extractedParameters.get(SubCommands.ID)));

                //check if to get analysis by id or timespan
                AnalyseResult result;
                if (!Arrays.asList(subCommands).contains(SubCommands.END_DATE.name()))
                {
                    //analyse by id
                    result = restaurantTableBookingAnalyser.analyse(restaurantTable);
                }
                else
                {
                    //analyse by timespan
                    result = restaurantTableBookingAnalyser.analyseBetweenDate(
                            restaurantTable,
                            Utils.createDateTime(extractedParameters.get(SubCommands.START_DATE)),
                            Utils.createDateTime(extractedParameters.get(SubCommands.END_DATE))
                    );
                }
                System.out.println("Restaurant table booking analysis result:\n" + result.toString());
            }
        }
    }

    private SessionFactory getSessionFactory()
    {
        HibernateSessionFactoryBuilder factoryBuilder = new HibernateSessionFactoryBuilder();
        return factoryBuilder.createSessionFactory();
    }
}
