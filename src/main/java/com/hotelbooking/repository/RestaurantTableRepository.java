package com.hotelbooking.repository;

import com.hotelbooking.model.RestaurantTable;
import org.hibernate.SessionFactory;

public class RestaurantTableRepository extends Repository<RestaurantTable, Integer> implements IRestaurantTableRepository
{
    public RestaurantTableRepository(SessionFactory sessionFactory){
        super(sessionFactory, RestaurantTable.class);
    }
}