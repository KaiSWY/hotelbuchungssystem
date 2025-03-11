package com.hotelbooking.service;

import com.hotelbooking.model.Booking;

public interface Bookable
{
    void book(Booking booking);
    void cancel();
}
