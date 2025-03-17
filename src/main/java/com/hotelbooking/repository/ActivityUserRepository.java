package com.hotelbooking.repository;

import com.hotelbooking.model.Activity_User;
import org.hibernate.SessionFactory;

public class ActivityUserRepository extends Repository<Activity_User, Integer>
{
    public ActivityUserRepository(SessionFactory sessionFactory)
    {
        super(sessionFactory, Activity_User.class);
    }
}
