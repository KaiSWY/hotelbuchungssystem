package com.hotelbooking.controller;

import com.hotelbooking.model.RestaurantTable_User;
import com.hotelbooking.service.BookingService;
import com.hotelbooking.service.analysers.AnalyseResult;
import com.hotelbooking.service.analysers.BookingAnalyser;

public class RestaurantTableBookingController implements IController<RestaurantTable_User, Integer>
{
    private final BookingService<RestaurantTable_User> restaurantTableBookingService;
    private final BookingAnalyser<RestaurantTable_User, Integer> restaurantTableBookingAnalyser;

    public RestaurantTableBookingController(BookingService<RestaurantTable_User> restaurantTableBookingService, BookingAnalyser<RestaurantTable_User, Integer> restaurantTableBookingAnalyser)
    {
        this.restaurantTableBookingService = restaurantTableBookingService;
        this.restaurantTableBookingAnalyser = restaurantTableBookingAnalyser;
    }

    @Override
    public RestaurantTable_User get(Integer integer)
    {
        return restaurantTableBookingService.getById(integer);
    }

    @Override
    public void post(RestaurantTable_User entity)
    {
        restaurantTableBookingService.book(entity);
    }

    @Override
    public void delete(Integer entity)
    {
        restaurantTableBookingService.cancel(entity);
    }

    @Override
    public AnalyseResult getAnalysis(Integer integer)
    {
        RestaurantTable_User entity = restaurantTableBookingService.getById(integer);
        return restaurantTableBookingAnalyser.analyse(entity);
    }
}
