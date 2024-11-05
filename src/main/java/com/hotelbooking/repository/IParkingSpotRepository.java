package com.hotelbooking.repository;

import com.hotelbooking.model.ParkingSpot;
import java.util.List;

public interface IParkingSpotRepository
{
    void add(ParkingSpot parkingSpot);
    void update(ParkingSpot parkingSpot);
    ParkingSpot getById(Integer id);
    List<ParkingSpot> getAll();
}
