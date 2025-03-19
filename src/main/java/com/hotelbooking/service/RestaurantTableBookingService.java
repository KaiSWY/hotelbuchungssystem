package com.hotelbooking.service;

import com.hotelbooking.model.Booking;
import com.hotelbooking.model.RestaurantTable;
import com.hotelbooking.model.RestaurantTable_User;
import com.hotelbooking.model.User;
import com.hotelbooking.repository.IRepository;

import java.time.LocalDateTime;
import java.util.List;

public class RestaurantTableBookingService extends BookingService<RestaurantTable_User>
{
    private final IRepository<User, Integer> userRepository;
    private final IRepository<RestaurantTable, Integer> restaurantTableRepository;

    public RestaurantTableBookingService(
            IRepository<RestaurantTable_User, Integer> restaurantTableUserRepository,
            IRepository<User, Integer> userRepository,
            IRepository<RestaurantTable, Integer> restaurantTableRepository)
    {
        super(restaurantTableUserRepository);
        this.userRepository = userRepository;
        this.restaurantTableRepository = restaurantTableRepository;
    }

    @Override
    public void book(Booking booking)
    {
        RestaurantTable_User restaurantTableUserBooking;
        if (booking instanceof RestaurantTable_User)
        {
            restaurantTableUserBooking = (RestaurantTable_User) booking;
        }
        else
        {
            throw new IllegalArgumentException("Invalid booking object");
        }

        User user = userRepository.getById(restaurantTableUserBooking.getUser().getUserId());
        // .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        RestaurantTable table = restaurantTableRepository.getById(restaurantTableUserBooking.getTable().getTableNumber());
        // .orElseThrow(() -> new IllegalArgumentException("Table not found with id: " + tableId));
        LocalDateTime startDateTime = booking.getStartDateTime();
        LocalDateTime endDateTime = booking.getEndDateTime();

        if (!isBookingConflict(table.getTableNumber(), startDateTime, endDateTime))
        {
            RestaurantTable_User tableUser = new RestaurantTable_User(table, user, startDateTime, endDateTime);
            repository.add(tableUser);
        }
        else
        {
            throw new IllegalArgumentException("Table is not available for the selected time period");
        }
    }

    @Override
    public void cancel(Integer bookingId)
    {
        RestaurantTable_User restaurantTableUser = repository.getById(bookingId);
        if (restaurantTableUser == null)
        {
            throw new IllegalArgumentException("Cancellation process failed. Restaurant table booking not found with ID: " + bookingId);
        }
        persistDeleteRestaurantTableUser(restaurantTableUser);
    }

    @Override
    public List<RestaurantTable_User> getBookingsByEntityId(Integer entityId)
    {
        return restaurantTableRepository.getById(entityId).getTables_users();
    }

    private void persistDeleteRestaurantTableUser(RestaurantTable_User restaurantTableUser)
    {
        User user = restaurantTableUser.getUser();
        RestaurantTable restaurantTable = restaurantTableUser.getTable();

        user.getTables_users().remove(restaurantTableUser);
        userRepository.update(user);
        restaurantTable.getTables_users().remove(restaurantTableUser);
        restaurantTableRepository.update(restaurantTable);
        super.cancel(restaurantTableUser.getId());
    }
}
