package com.hotelbooking.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "room_user")
public class Room_User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "room_number")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public Room_User(Room room, User user, LocalDateTime startDateTime, LocalDateTime endDateTime)
    {
        this.room = room;
        this.user = user;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public Room_User()
    {
    }

    public long id()
    {
        return id;
    }

    public void id(long id)
    {
        this.id = id;
    }

    public Room room()
    {
        return room;
    }

    public void room(Room room)
    {
        this.room = room;
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
