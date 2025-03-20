package com.hotelbooking.service;

import com.hotelbooking.model.Booking;
import com.hotelbooking.repository.IRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class BookingService<T extends Booking> implements Bookable<T>
{
    protected final IRepository<T, Integer> repository;

    protected BookingService(IRepository<T, Integer> repository)
    {
        this.repository = repository;
    }

    @Override
    public abstract void book(Booking booking);

    @Override
    public void cancel(Integer bookingId)
    {
        T booking = repository.getById(bookingId);
        if (booking != null)
        {
            repository.deleteById(booking.getId());
        }
        else
        {
            throw new RuntimeException("Booking not found with ID: " + bookingId);
        }
    }

    protected boolean isBookingConflict(Integer entityId, LocalDateTime start, LocalDateTime end)
    {
        List<Booking> bookings = getBookingsByEntityId(entityId).stream().map(x -> (Booking) x).toList();
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

    protected boolean timeInTimeSpan(LocalDateTime localDateTime, LocalDateTime startTimeSpan, LocalDateTime endTimeSpan)
    {
        return localDateTime.isAfter(startTimeSpan) && localDateTime.isBefore(endTimeSpan);
    }

    public List<T> getBookingsByTimeSpan(LocalDateTime startDateTime, LocalDateTime endDateTime)
    {
        List<T> allEntities = repository.getAll();
        List<T> filteredBookings = new ArrayList<>();
        for (T entity : allEntities)
        {
            if (timeInTimeSpan(startDateTime, entity.getStartDateTime(), entity.getEndDateTime())
                    || timeInTimeSpan(endDateTime, entity.getStartDateTime(), entity.getEndDateTime()))
            {
                filteredBookings.add(entity);
            }
        }
        return filteredBookings;
    }

    public List<T> findAll()
    {
        return repository.getAll();
    }

    public T getById(Integer id)
    {
        return repository.getById(id);
    }
}
