package com.hotelbooking.controller;

import com.hotelbooking.model.Activity_User;
import com.hotelbooking.service.BookingService;
import com.hotelbooking.service.analysers.AnalyseResult;
import com.hotelbooking.service.analysers.BookingAnalyser;

public class ActivityBookingController implements IController<Activity_User, Integer>
{
    private final BookingService<Activity_User> activityBookingService;
    private final BookingAnalyser<Activity_User, Integer> activityBookingAnalyser;

    public ActivityBookingController(BookingService<Activity_User> activityBookingService, BookingAnalyser<Activity_User, Integer> activityBookingAnalyser)
    {
        this.activityBookingService = activityBookingService;
        this.activityBookingAnalyser = activityBookingAnalyser;
    }

    @Override
    public Activity_User get(Integer integer)
    {
        return activityBookingService.getById(integer);
    }

    @Override
    public void post(Activity_User entity)
    {
        activityBookingService.book(entity);
    }

    @Override
    public void delete(Integer entity)
    {
        activityBookingService.cancel(entity);
    }

    @Override
    public AnalyseResult getAnalysis(Integer integer)
    {
        Activity_User entity = activityBookingService.getById(integer);
        return activityBookingAnalyser.analyse(entity);
    }
}
