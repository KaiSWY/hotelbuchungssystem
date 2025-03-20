package com.hotelbooking.service.analysers.implementations;

import com.hotelbooking.model.Booking;
import com.hotelbooking.model.RestaurantTable;
import com.hotelbooking.repository.IRepository;
import com.hotelbooking.service.analysers.AnalyseResult;
import com.hotelbooking.service.analysers.BookingAnalyser;

import java.time.LocalDateTime;

public class RestaurantTableBookingAnalyser extends BookingAnalyser<RestaurantTable, Integer>
{
    protected RestaurantTableBookingAnalyser(IRepository<RestaurantTable, Integer> repository, IRepository<Booking, Integer> bookingRepository)
    {
        super(repository, bookingRepository);
    }

    @Override
    public AnalyseResult analyseBetweenDate(RestaurantTable entity, LocalDateTime startTime, LocalDateTime endTime)
    {
        return null;
    }
}
