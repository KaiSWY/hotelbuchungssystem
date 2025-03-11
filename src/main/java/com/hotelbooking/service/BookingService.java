package com.hotelbooking.service;

import com.hotelbooking.model.Booking;

import java.time.LocalDateTime;
import java.util.List;

public abstract class BookingService<ID> implements Bookable
{
    @Override
    public abstract void book(Booking booking);

    @Override
    public abstract void cancel();

    protected boolean isBookingConflict(ID entityId, LocalDateTime start, LocalDateTime end)
    {
        List<Booking> bookings = getBookingsFromEntity(entityId);
        for (Booking booking : bookings)
        {
            if (timeInTimeSpan(start, booking.getStartDateTime(), booking.getEndDateTime())
                    || timeInTimeSpan(end, booking.getStartDateTime(), booking.getEndDateTime()))
            {
                return true;
            }
        }
        return false;
    }

    private boolean timeInTimeSpan(LocalDateTime localDateTime, LocalDateTime startTimeSpan, LocalDateTime endTimeSpan)
    {
        return localDateTime.isAfter(startTimeSpan) && localDateTime.isBefore(endTimeSpan);
    }

    protected abstract List<Booking> getBookingsFromEntity(ID entityId);
}
