package com.hotelbooking.service;

import com.hotelbooking.model.Booking;

import java.time.LocalDateTime;
import java.util.List;

public interface Bookable<T>
{
    void book(Booking booking);

    void cancel(Integer bookingId);

    List<T> getBookingsByEntityId(Integer entityId);

    List<T> getBookingsByTimeSpan(LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<T> findAll();

    T getById(Integer id);
}
