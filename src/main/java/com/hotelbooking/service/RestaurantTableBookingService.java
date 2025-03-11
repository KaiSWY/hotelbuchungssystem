package com.hotelbooking.service;

import com.hotelbooking.model.*;
import com.hotelbooking.repository.*;

import java.time.LocalDateTime;
import java.util.List;

public class RestaurantTableBookingService extends BookingService<Integer>
{
    private final IUserRepository userRepository;
    private final IRestaurantTableRepository restaurantTableRepository;

    public RestaurantTableBookingService(IUserRepository userRepository, IRestaurantTableRepository restaurantTableRepository) {
        this.userRepository = userRepository;
        this.restaurantTableRepository = restaurantTableRepository;
    }

    // TODO: Error handling
    @Override
    public void book(Booking booking) {
        RestaurantTable_User restaurantTableUserBooking;
        if (booking instanceof RestaurantTable_User) {
            restaurantTableUserBooking = (RestaurantTable_User) booking;
        } else {
            // TODO Required error handling for wrong booking object
            throw new IllegalArgumentException("Invalid booking object");
        }

        User user = userRepository.getById(restaurantTableUserBooking.getUser().getUserId());
        // .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        RestaurantTable table = restaurantTableRepository.getById(restaurantTableUserBooking.getTable().getTableNumber());
        // .orElseThrow(() -> new IllegalArgumentException("Table not found with id: " + tableId));
        LocalDateTime startDateTime = booking.getStartDateTime();
        LocalDateTime endDateTime = booking.getEndDateTime();

        if (!isBookingConflict(table.getTableNumber(), startDateTime, endDateTime)) {
            RestaurantTable_User tableUser = new RestaurantTable_User(table, user, startDateTime, endDateTime);
            user.getTables_users().add(tableUser); // assuming user has a method to access the booking
            userRepository.update(user);
        } else {
            throw new IllegalArgumentException("Table is not available for the selected time period");
        }
    }

    @Override
    public void cancel()
    {

    }

    @Override
    protected List<Booking> getBookingsFromEntity(Integer entityId)
    {
        return restaurantTableRepository.getById(entityId).getTables_users().stream().map(x -> (Booking) x).toList();
    }
}
