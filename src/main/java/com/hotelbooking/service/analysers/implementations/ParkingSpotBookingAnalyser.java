package com.hotelbooking.service.analysers.implementations;

import com.hotelbooking.model.Booking;
import com.hotelbooking.model.ParkingSpot;
import com.hotelbooking.model.ParkingSpot_User;
import com.hotelbooking.repository.IRepository;
import com.hotelbooking.service.analysers.BookingAnalyser;

import java.time.LocalDateTime;
import java.util.List;

public class ParkingSpotBookingAnalyser extends BookingAnalyser<ParkingSpot, Integer>
{
    protected ParkingSpotBookingAnalyser(IRepository<ParkingSpot, Integer> repository, IRepository<Booking, Integer> bookingRepository)
    {
        super(repository, bookingRepository);
    }

    @Override
    protected List<Booking> getFilteredBookings(List<Booking> bookings, ParkingSpot entity, LocalDateTime startTime, LocalDateTime endTime)
    {
        List<ParkingSpot_User> parkingSpotUserBookings = convertBookingListToExtendsBookingList(bookings, ParkingSpot_User.class);
        parkingSpotUserBookings = parkingSpotUserBookings.stream()
                .filter(booking -> booking.getSpot().getSpotNumber() == entity.getSpotNumber())
                .filter(booking -> !booking.getEndDateTime().isBefore(startTime) && !booking.getStartDateTime().isAfter(endTime))
                .toList();

        return convertExtendsBookingListToBookingList(parkingSpotUserBookings);
    }
}
