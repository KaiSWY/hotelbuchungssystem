package com.hotelbooking.cli.interaction_classes;

import com.hotelbooking.HibernateSessionFactoryBuilder;
import com.hotelbooking.cli.Utils;
import com.hotelbooking.cli.enums.MainCommands;
import com.hotelbooking.cli.enums.SubCommands;
import com.hotelbooking.model.Activity;
import com.hotelbooking.model.Activity_User;
import com.hotelbooking.model.Booking;
import com.hotelbooking.model.User;
import com.hotelbooking.repository.ActivityRepository;
import com.hotelbooking.repository.ActivityUserRepository;
import com.hotelbooking.repository.IRepository;
import com.hotelbooking.repository.UserRepository;
import com.hotelbooking.service.ActivityBookingService;
import com.hotelbooking.service.Bookable;
import com.hotelbooking.service.analysers.AnalyseResult;
import com.hotelbooking.service.analysers.IBookingAnalyser;
import com.hotelbooking.service.analysers.implementations.ActivityBookingAnalyser;
import org.hibernate.SessionFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.hotelbooking.cli.HotelBookingCLI.extractParameters;

public class ActivityBookingInteraction implements IBasicMethods
{
    //parameters
    private String mainCommand;
    private String[] subValues;
    private String[] subCommands;

    //initialize repositories
    private IRepository<Activity_User, Integer> activityUserRepository;
    private IRepository<User, Integer> userRepository;
    private IRepository<Activity, Integer> activityRepository;
    private Bookable<Activity_User> activityBookingService;
    private IBookingAnalyser<Activity> activityBookingAnalyser;

    public ActivityBookingInteraction()
    {
    }

    public ActivityBookingInteraction(String mainCommand, String[] subValues, String[] subCommands)
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

                this.activityUserRepository = new ActivityUserRepository(sessionFactory);
                this.userRepository = new UserRepository(sessionFactory);
                this.activityRepository = new ActivityRepository(sessionFactory);

                this.activityBookingService = new ActivityBookingService(
                        activityUserRepository,
                        userRepository,
                        activityRepository
                );

                User user = this.userRepository.getById(Utils.createNumber(extractedParameters.get(SubCommands.ID)));
                Activity activity = this.activityRepository.getById(Utils.createNumber(extractedParameters.get(SubCommands.ID)));

                Booking activityBooking = new Activity_User(
                        activity,
                        user,
                        Utils.createDateTime(extractedParameters.get(SubCommands.START_DATE)),
                        Utils.createDateTime(extractedParameters.get(SubCommands.END_DATE))
                );

                this.activityBookingService.book(activityBooking);

                System.out.println("Activity booked!");
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

                this.activityUserRepository = new ActivityUserRepository(sessionFactory);
                this.userRepository = new UserRepository(sessionFactory);
                this.activityRepository = new ActivityRepository(sessionFactory);

                this.activityBookingService = new ActivityBookingService(
                        activityUserRepository,
                        userRepository,
                        activityRepository
                );

                //check if to get bookings by id or timespan
                if (!Arrays.asList(subCommands).contains(SubCommands.END_DATE.name()))
                {
                    Activity_User activityUser = this.activityBookingService.getById(Utils.createNumber(extractedParameters.get(SubCommands.ID)));
                    System.out.println(activityUser.toString());
                }
                else
                {
                    List<Activity_User> activityUserList = this.activityBookingService.getBookingsByTimeSpan(
                            Utils.createDateTime(extractedParameters.get(SubCommands.START_DATE)),
                            Utils.createDateTime(extractedParameters.get(SubCommands.END_DATE))
                    );

                    //get bookings by timespan
                    for (Activity_User currentActivityUser : activityUserList)
                    {
                        System.out.println(currentActivityUser.toString());
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

                this.activityUserRepository = new ActivityUserRepository(sessionFactory);
                this.userRepository = new UserRepository(sessionFactory);
                this.activityRepository = new ActivityRepository(sessionFactory);

                this.activityBookingService = new ActivityBookingService(
                        activityUserRepository,
                        userRepository,
                        activityRepository
                );

                this.activityBookingService.cancel(Utils.createNumber(extractedParameters.get(SubCommands.ID)));

                System.out.println("Activity booking deleted!");
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

                this.activityRepository = new ActivityRepository(sessionFactory);
                this.activityUserRepository = new ActivityUserRepository(sessionFactory);

                activityBookingAnalyser = new ActivityBookingAnalyser(
                        this.activityRepository,
                        this.activityUserRepository
                );

                Activity activity = this.activityRepository.getById(Utils.createNumber(extractedParameters.get(SubCommands.ID)));

                //check if to get analysis by id or timespan
                AnalyseResult result;
                if (!Arrays.asList(subCommands).contains(SubCommands.END_DATE.name()))
                {
                    //analyse by id
                    result = activityBookingAnalyser.analyse(activity);
                }
                else
                {
                    //analyse by timespan
                    result = activityBookingAnalyser.analyseBetweenDate(
                            activity,
                            Utils.createDateTime(extractedParameters.get(SubCommands.START_DATE)),
                            Utils.createDateTime(extractedParameters.get(SubCommands.END_DATE))
                    );
                }
                System.out.println("Activity booking analysis result:\n" + result.toString());
            }
        }
    }

    private SessionFactory getSessionFactory()
    {
        HibernateSessionFactoryBuilder factoryBuilder = new HibernateSessionFactoryBuilder();
        return factoryBuilder.createSessionFactory();
    }
}
