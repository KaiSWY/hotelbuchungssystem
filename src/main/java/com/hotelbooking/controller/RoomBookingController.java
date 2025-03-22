package com.hotelbooking.controller;

import com.hotelbooking.model.Booking;
import com.hotelbooking.model.Room_User;
import com.hotelbooking.service.RoomBookingService;

import java.util.List;

public class RoomBookingController implements IController
{
    private final RoomBookingService roomBookingService;

    public RoomBookingController(RoomBookingService roomBookingService)
    {
        this.roomBookingService = roomBookingService;
    }

    @Override
    public Object get(Object o) {
        return this.roomBookingService.getBookingsByEntityId();
    }

    @Override
    public void post(Object entity) {

    }

    @Override
    public void delete(Object entity) {

    }

    @Override
    public Object getAnalysis(Object o) {
        return null;
    }
}
