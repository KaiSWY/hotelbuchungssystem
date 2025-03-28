package com.hotelbooking.service.analysers.implementations;

import com.hotelbooking.model.Activity;
import com.hotelbooking.model.Activity_User;
import com.hotelbooking.model.Booking;
import com.hotelbooking.repository.IRepository;
import com.hotelbooking.service.analysers.BookingAnalyser;

import java.time.LocalDateTime;
import java.util.List;

public class ActivityBookingAnalyser extends BookingAnalyser<Activity_User, Activity, Integer>
{
    public ActivityBookingAnalyser(IRepository<Activity, Integer> repository, IRepository<Activity_User, Integer> bookingRepository)
    {
        super(repository, bookingRepository);
    }

    @Override
    protected List<Booking> getFilteredBookings(List<Activity_User> bookings, Activity entity, LocalDateTime startTime, LocalDateTime endTime)
    {
        bookings = bookings.stream()
                .filter(booking -> booking.getActivity().getActivityId() == entity.getActivityId())
                .filter(booking -> !booking.getEndDateTime().isBefore(startTime) && !booking.getStartDateTime().isAfter(endTime))
                .toList();

        return convertExtendsBookingListToBookingList(bookings);
    }
}

