package com.hotelbooking.service;

import com.hotelbooking.model.Booking;

import java.time.LocalDateTime;
import java.util.List;

public interface Bookable<T, ID>
{
    void book(Booking booking);

    void cancel(Integer bookingId);

    List<T> getBookingsByEntityId(ID entityId);

    List<T> getBookingsByTimeSpan(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
