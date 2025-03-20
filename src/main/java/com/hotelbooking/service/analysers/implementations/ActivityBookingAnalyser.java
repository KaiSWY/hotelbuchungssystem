package com.hotelbooking.service.analysers.implementations;

import com.hotelbooking.model.Activity;
import com.hotelbooking.model.Booking;
import com.hotelbooking.repository.IRepository;
import com.hotelbooking.service.analysers.AnalyseResult;
import com.hotelbooking.service.analysers.BookingAnalyser;

import java.time.LocalDateTime;

public class ActivityBookingAnalyser extends BookingAnalyser<Activity, Integer>
{
    protected ActivityBookingAnalyser(IRepository<Activity, Integer> repository, IRepository<Booking, Integer> bookingRepository)
    {
        super(repository, bookingRepository);
    }

    @Override
    public AnalyseResult analyseBetweenDate(Activity entity, LocalDateTime startTime, LocalDateTime endTime)
    {
        return null;
    }
}
