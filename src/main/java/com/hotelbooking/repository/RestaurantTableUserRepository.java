package com.hotelbooking.repository;

import com.hotelbooking.model.RestaurantTable_User;
import org.hibernate.SessionFactory;

public class RestaurantTableUserRepository extends Repository<RestaurantTable_User, Integer>
{
    public RestaurantTableUserRepository(SessionFactory sessionFactory)
    {
        super(sessionFactory, RestaurantTable_User.class);
    }
}
