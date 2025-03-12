package com.hotelbooking.repository;

import com.hotelbooking.model.User;
import org.hibernate.SessionFactory;

public class UserRepository extends Repository<User, Integer>
{
    public UserRepository(SessionFactory sessionFactory)
    {
        super(sessionFactory, User.class);
    }
}
