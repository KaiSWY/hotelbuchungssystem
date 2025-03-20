package com.hotelbooking.repository;

import com.hotelbooking.model.Activity;
import org.hibernate.SessionFactory;

public class ActivityRepository extends Repository<Activity, Integer>
{
    public ActivityRepository(SessionFactory sessionFactory)
    {
        super(sessionFactory, Activity.class);
    }
}
