package com.hotelbooking.controller;

import com.hotelbooking.model.Room_User;
import com.hotelbooking.service.BookingService;
import com.hotelbooking.service.analysers.AnalyseResult;
import com.hotelbooking.service.analysers.BookingAnalyser;


public class RoomBookingController implements IController<Room_User, Integer>
{
    private final BookingService<Room_User> roomBookingService;
    private final BookingAnalyser<Room_User, Integer> roomBookingAnalyser;

    public RoomBookingController(BookingService<Room_User> roomBookingService, BookingAnalyser<Room_User, Integer> roomBookingAnalyser)
    {
        this.roomBookingService = roomBookingService;
        this.roomBookingAnalyser = roomBookingAnalyser;
    }

    @Override
    public Room_User get(Integer integer)
    {
        return roomBookingService.getById(integer);
    }

    @Override
    public void post(Room_User entity)
    {
        roomBookingService.book(entity);
    }

    @Override
    public void delete(Integer entity)
    {
        roomBookingService.cancel(entity);
    }

    @Override
    public AnalyseResult getAnalysis(Integer integer)
    {
        Room_User entity = roomBookingService.getById(integer);
        return roomBookingAnalyser.analyse(entity);
    }
}
