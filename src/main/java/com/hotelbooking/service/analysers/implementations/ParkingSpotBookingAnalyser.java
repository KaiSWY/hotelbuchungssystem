package com.hotelbooking.service.analysers.implementations;

import com.hotelbooking.model.Booking;
import com.hotelbooking.model.ParkingSpot;
import com.hotelbooking.model.ParkingSpot_User;
import com.hotelbooking.repository.IRepository;
import com.hotelbooking.service.analysers.BookingAnalyser;

import java.time.LocalDateTime;
import java.util.List;

public class ParkingSpotBookingAnalyser extends BookingAnalyser<ParkingSpot_User, ParkingSpot, Integer>
{
    public ParkingSpotBookingAnalyser(IRepository<ParkingSpot, Integer> repository, IRepository<ParkingSpot_User, Integer> bookingRepository)
    {
        super(repository, bookingRepository);
    }

    @Override
    protected List<Booking> getFilteredBookings(List<ParkingSpot_User> bookings, ParkingSpot entity, LocalDateTime startTime, LocalDateTime endTime)
    {
        bookings = bookings.stream()
                .filter(booking -> booking.getSpot().getSpotNumber() == entity.getSpotNumber())
                .filter(booking -> !booking.getEndDateTime().isBefore(startTime) && !booking.getStartDateTime().isAfter(endTime))
                .toList();

        return convertExtendsBookingListToBookingList(bookings);
    }
}
