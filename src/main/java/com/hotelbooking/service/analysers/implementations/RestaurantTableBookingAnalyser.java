package com.hotelbooking.service.analysers.implementations;

import com.hotelbooking.model.Booking;
import com.hotelbooking.model.RestaurantTable;
import com.hotelbooking.model.RestaurantTable_User;
import com.hotelbooking.repository.IRepository;
import com.hotelbooking.service.analysers.BookingAnalyser;

import java.time.LocalDateTime;
import java.util.List;

public class RestaurantTableBookingAnalyser extends BookingAnalyser<RestaurantTable, Integer>
{
    public RestaurantTableBookingAnalyser(IRepository<RestaurantTable, Integer> repository, IRepository<Booking, Integer> bookingRepository)
    {
        super(repository, bookingRepository);
    }

    @Override
    protected List<Booking> getFilteredBookings(List<Booking> bookings, RestaurantTable entity, LocalDateTime startTime, LocalDateTime endTime)
    {
        List<RestaurantTable_User> tableUserBookings = convertBookingListToExtendsBookingList(bookings, RestaurantTable_User.class);
        tableUserBookings = tableUserBookings.stream()
                .filter(booking -> booking.getTable().getTableNumber() == entity.getTableNumber())
                .filter(booking -> !booking.getEndDateTime().isBefore(startTime) && !booking.getStartDateTime().isAfter(endTime))
                .toList();

        return convertExtendsBookingListToBookingList(tableUserBookings);
    }
}
