package com.hotelbooking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "restaurant_table_user")
public class RestaurantTable_User extends Booking
{
    @ManyToOne
    @JoinColumn(name = "table_number", nullable = false)
    private RestaurantTable table;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public RestaurantTable_User()
    {
        super();
    }

    public RestaurantTable_User(RestaurantTable table, User user, LocalDateTime startDateTime, LocalDateTime endDateTime)
    {
        super(startDateTime, endDateTime);
        this.table = table;
        this.user = user;
    }

    public RestaurantTable getTable()
    {
        return table;
    }

    public void setTable(RestaurantTable table)
    {
        this.table = table;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    @Override
    public String toString() {
        return "RestaurantT table information:" +
                "\n" + table.toString() +
                "\n" + user.toString() +
                "\nBooking start date: " + getStartDateTime() +
                "\nBooking end date: " + getEndDateTime();
    }
}
