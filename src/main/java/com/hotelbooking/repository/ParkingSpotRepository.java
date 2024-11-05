package com.hotelbooking.repository;

import com.hotelbooking.model.ParkingSpot;
import org.hibernate.SessionFactory;

public class ParkingSpotRepository extends Repository<ParkingSpot, Integer> implements IParkingSpotRepository
{
    public ParkingSpotRepository(SessionFactory sessionFactory){
        super(sessionFactory, ParkingSpot.class);
    }
}
