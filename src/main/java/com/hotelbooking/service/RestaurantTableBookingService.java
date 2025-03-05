package com.hotelbooking.service;

import com.hotelbooking.model.*;
import com.hotelbooking.repository.*;

import java.time.LocalDateTime;
import java.util.List;

public class RestaurantTableBookingService
{
    private final IUserRepository userRepository;
    private final IRestaurantTableRepository restaurantTableRepository;

    public RestaurantTableBookingService(IUserRepository userRepository, IRestaurantTableRepository restaurantTableRepository) {
        this.userRepository = userRepository;
        this.restaurantTableRepository = restaurantTableRepository;
    }

    // TODO: Error handling
    public void book(int userId, int tableId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        User user = userRepository.getById(userId);
        // .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        RestaurantTable table = restaurantTableRepository.getById(tableId);
        // .orElseThrow(() -> new IllegalArgumentException("Table not found with id: " + tableId));

        if (isBookingConflict(table, startDateTime, endDateTime)) {
            RestaurantTable_User tableUser = new RestaurantTable_User(table, user, startDateTime, endDateTime);
            user.tables_users().add(tableUser); // assuming user has a method to access the booking
            userRepository.update(user);
        } else {
            throw new IllegalArgumentException("Table is not available for the selected time period");
        }
    }

    private boolean isBookingConflict(RestaurantTable table, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        List<RestaurantTable_User> conflictingBookings = restaurantTableRepository.findConflictingBookings(table, startDateTime, endDateTime);
        return conflictingBookings.isEmpty();
    }
}
