package com.hotelbooking.repository;

import com.hotelbooking.model.User;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class UserRepository extends Repository<User, Integer>
{
    public UserRepository(SessionFactory sessionFactory)
    {
        super(sessionFactory, User.class);
    }

    public Optional<User> getUserByEmail(String email)
    {
        List<User> users = getAll();
        for (User user : users)
        {
            if (user.getEmail().equals(email))
            {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
