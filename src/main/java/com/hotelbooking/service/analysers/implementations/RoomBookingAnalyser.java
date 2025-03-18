package com.hotelbooking.service.analysers.implementations;

import com.hotelbooking.model.Booking;
import com.hotelbooking.model.Room;
import com.hotelbooking.repository.IRepository;
import com.hotelbooking.service.analysers.AnalyseResult;
import com.hotelbooking.service.analysers.BookingAnalyser;

import java.time.LocalDateTime;

public class RoomBookingAnalyser extends BookingAnalyser<Room, Integer>
{
    protected RoomBookingAnalyser(IRepository<Room, Integer> repository, IRepository<Booking, Integer> bookingRepository)
    {
        super(repository, bookingRepository);
    }

    @Override
    public AnalyseResult analyseBetweenDate(Room entity, LocalDateTime startTime, LocalDateTime endTime)
    {
        return null;
    }
}
