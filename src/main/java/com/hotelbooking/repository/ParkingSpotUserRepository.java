package com.hotelbooking.repository;

import com.hotelbooking.model.ParkingSpot_User;
import org.hibernate.SessionFactory;

public class ParkingSpotUserRepository extends Repository<ParkingSpot_User, Integer>
{
    public ParkingSpotUserRepository(SessionFactory sessionFactory)
    {
        super(sessionFactory, ParkingSpot_User.class);
    }
}
