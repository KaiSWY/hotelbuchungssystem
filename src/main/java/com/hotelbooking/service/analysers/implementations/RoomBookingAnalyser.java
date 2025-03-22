package com.hotelbooking.service.analysers.implementations;

import com.hotelbooking.model.Booking;
import com.hotelbooking.model.Room;
import com.hotelbooking.model.Room_User;
import com.hotelbooking.repository.IRepository;
import com.hotelbooking.service.analysers.BookingAnalyser;

import java.time.LocalDateTime;
import java.util.List;

public class RoomBookingAnalyser extends BookingAnalyser<Room, Integer>
{
    public RoomBookingAnalyser(IRepository<Room, Integer> repository, IRepository<Booking, Integer> bookingRepository)
    {
        super(repository, bookingRepository);
    }

    @Override
    protected List<Booking> getFilteredBookings(List<Booking> bookings, Room entity, LocalDateTime startTime, LocalDateTime endTime)
    {
        List<Room_User> roomUserBookings = convertBookingListToExtendsBookingList(bookings, Room_User.class);
        roomUserBookings = roomUserBookings.stream()
                .filter(booking -> booking.getRoom().getRoomNumber() == entity.getRoomNumber())
                .filter(booking -> !booking.getEndDateTime().isBefore(startTime) && !booking.getStartDateTime().isAfter(endTime))
                .toList();

        return convertExtendsBookingListToBookingList(roomUserBookings);
    }
}
