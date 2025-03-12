package com.hotelbooking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "room_user")
public class Room_User extends Booking
{
    @ManyToOne
    @JoinColumn(name = "room_number")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Room_User(Room room, User user, LocalDateTime startDateTime, LocalDateTime endDateTime)
    {
        super(startDateTime, endDateTime);
        this.room = room;
        this.user = user;
    }

    public Room_User()
    {
        super();
    }

    public Room getRoom()
    {
        return room;
    }

    public void setRoom(Room room)
    {
        this.room = room;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
