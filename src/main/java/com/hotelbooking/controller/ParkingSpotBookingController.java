package com.hotelbooking.controller;

import com.hotelbooking.model.ParkingSpot_User;
import com.hotelbooking.service.BookingService;
import com.hotelbooking.service.analysers.AnalyseResult;
import com.hotelbooking.service.analysers.BookingAnalyser;

public class ParkingSpotBookingController implements IController<ParkingSpot_User, Integer>
{
    private final BookingService<ParkingSpot_User> parkingSpotBookingService;
    private final BookingAnalyser<ParkingSpot_User, Integer> parkingSpotBookingAnalyser;

    public ParkingSpotBookingController(BookingService<ParkingSpot_User> parkingSpotBookingService, BookingAnalyser<ParkingSpot_User, Integer> parkingSpotBookingAnalyser)
    {
        this.parkingSpotBookingService = parkingSpotBookingService;
        this.parkingSpotBookingAnalyser = parkingSpotBookingAnalyser;
    }

    @Override
    public ParkingSpot_User get(Integer integer)
    {
        return parkingSpotBookingService.getById(integer);
    }

    @Override
    public void post(ParkingSpot_User entity)
    {
        parkingSpotBookingService.book(entity);
    }

    @Override
    public void delete(Integer entity)
    {
        parkingSpotBookingService.cancel(entity);
    }

    @Override
    public AnalyseResult getAnalysis(Integer integer)
    {
        ParkingSpot_User entity = parkingSpotBookingService.getById(integer);
        return parkingSpotBookingAnalyser.analyse(entity);
    }
}
