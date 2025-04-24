package com.hotelbooking.cli.interaction_classes;

import com.hotelbooking.HibernateSessionFactoryFactory;
import com.hotelbooking.cli.Utils;
import com.hotelbooking.cli.enums.MainCommands;
import com.hotelbooking.cli.enums.SubCommands;
import com.hotelbooking.model.*;
import com.hotelbooking.repository.*;
import com.hotelbooking.service.UserRegistrationService;
import org.hibernate.SessionFactory;

import java.util.Map;

import static com.hotelbooking.cli.HotelBookingCLI.extractParameters;

public class UserInteraction implements IBasicMethods
{
    //parameters
    private String mainCommand;
    private String[] subValues;
    private String[] subCommands;

    //initialize repositories
    private IRepository<User, Integer> userRepository;
    private UserRegistrationService userRegistrationService;

    public UserInteraction()
    {
    }

    public UserInteraction(String mainCommand, String[] subValues, String[] subCommands)
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
                Map<SubCommands, String> extractedParameters = extractParameters(subValues);

                //create new registration
                User newUser = new com.hotelbooking.model.User(
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
                this.userRegistrationService = new UserRegistrationService(this.userRepository);
                this.userRegistrationService.create(newUser);

                System.out.println("User created!");
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

                this.userRepository = new UserRepository(sessionFactory);
                this.userRegistrationService = new UserRegistrationService(this.userRepository);
                User user = this.userRegistrationService.getById(Utils.createNumber(extractedParameters.get(SubCommands.ID)));

                System.out.println(user.toString());
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

                this.userRepository = new UserRepository(sessionFactory);
                this.userRegistrationService = new UserRegistrationService(userRepository);

                this.userRegistrationService.delete(Integer.parseInt(extractedParameters.get(SubCommands.ID)));

                //set information output
                System.out.println("User deleted!");
            }
        }
    }

    private SessionFactory getSessionFactory()
    {
        HibernateSessionFactoryFactory factoryBuilder = new HibernateSessionFactoryFactory();
        return factoryBuilder.createSessionFactory();
    }

    @Override
    public void analytics()
    {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
