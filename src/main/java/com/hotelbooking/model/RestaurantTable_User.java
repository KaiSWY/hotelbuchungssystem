package com.hotelbooking.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "restaurant_table_user")
public class RestaurantTable_User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "table_number", nullable = false)
    private RestaurantTable table;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "start_date_time")
    private LocalDateTime startDateTime;
    @Column(name = "end_date_time")
    private LocalDateTime endDateTime;

    public RestaurantTable_User()
    {
    }

    public RestaurantTable_User(RestaurantTable table, User user, LocalDateTime startDateTime, LocalDateTime endDateTime)
    {
        this.table = table;
        this.user = user;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public int id()
    {
        return id;
    }

    public void id(int id)
    {
        this.id = id;
    }

    public RestaurantTable table()
    {
        return table;
    }

    public void table(RestaurantTable table)
    {
        this.table = table;
    }

    public User user()
    {
        return user;
    }

    public void user(User user)
    {
        this.user = user;
    }

    public LocalDateTime startDateTime()
    {
        return startDateTime;
    }

    public void startDateTime(LocalDateTime startDateTime)
    {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime endDateTime()
    {
        return endDateTime;
    }

    public void endDateTime(LocalDateTime endDateTime)
    {
        this.endDateTime = endDateTime;
    }
}
