package com.hotelbooking.service.analysers.implementations;

import com.hotelbooking.model.Booking;
import com.hotelbooking.model.ParkingSpot;
import com.hotelbooking.repository.IRepository;
import com.hotelbooking.service.analysers.AnalyseResult;
import com.hotelbooking.service.analysers.BookingAnalyser;

import java.time.LocalDateTime;

public class ParkingSpotBookingAnalyser extends BookingAnalyser<ParkingSpot, Integer>
{
    protected ParkingSpotBookingAnalyser(IRepository<ParkingSpot, Integer> repository, IRepository<Booking, Integer> bookingRepository)
    {
        super(repository, bookingRepository);
    }

    @Override
    public AnalyseResult analyseBetweenDate(ParkingSpot entity, LocalDateTime startTime, LocalDateTime endTime)
    {
        return null;
    }
}
