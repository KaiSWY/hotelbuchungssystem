package com.hotelbooking.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "parking_spot_user")
public class ParkingSpot_User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "spot_id", nullable = false)
    private ParkingSpot spot;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public ParkingSpot_User()
    {
    }

    public ParkingSpot_User(ParkingSpot spot, User user, LocalDateTime startDateTime, LocalDateTime endDateTime)
    {
        this.spot = spot;
        this.user = user;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public long id()
    {
        return id;
    }

    public void id(long id)
    {
        this.id = id;
    }

    public ParkingSpot spot()
    {
        return spot;
    }

    public void spot(ParkingSpot spot)
    {
        this.spot = spot;
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