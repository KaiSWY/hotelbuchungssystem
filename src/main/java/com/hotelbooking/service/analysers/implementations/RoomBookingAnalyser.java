package com.hotelbooking.service.analysers.implementations;

import com.hotelbooking.model.Booking;
import com.hotelbooking.model.Room;
import com.hotelbooking.model.Room_User;
import com.hotelbooking.repository.IRepository;
import com.hotelbooking.service.analysers.BookingAnalyser;

import java.time.LocalDateTime;
import java.util.List;

public class RoomBookingAnalyser extends BookingAnalyser<Room_User, Room, Integer>
{
    public RoomBookingAnalyser(IRepository<Room, Integer> repository, IRepository<Room_User, Integer> bookingRepository)
    {
        super(repository, bookingRepository);
    }

    @Override
    protected List<Booking> getFilteredBookings(List<Room_User> bookings, Room entity, LocalDateTime startTime, LocalDateTime endTime)
    {
        bookings = bookings.stream()
                .filter(booking -> booking.getRoom().getRoomNumber() == entity.getRoomNumber())
                .filter(booking -> !booking.getEndDateTime().isBefore(startTime) && !booking.getStartDateTime().isAfter(endTime))
                .toList();

        return convertExtendsBookingListToBookingList(bookings);
    }
}
