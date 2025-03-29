package com.hotelbooking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "parking_spot_user")
public class ParkingSpot_User extends Booking
{
    @ManyToOne
    @JoinColumn(name = "spot_id", nullable = false)
    private ParkingSpot spot;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public ParkingSpot_User()
    {
        super();
    }

    public ParkingSpot_User(ParkingSpot spot, User user, LocalDateTime startDateTime, LocalDateTime endDateTime)
    {
        super(startDateTime, endDateTime);
        this.spot = spot;
        this.user = user;
    }

    public ParkingSpot getSpot()
    {
        return spot;
    }

    public void setSpot(ParkingSpot spot)
    {
        this.spot = spot;
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
        return "Parking Spot booking information:" +
                "\n" + spot.toString() +
                "\n" + user.toString() +
                "\nBooking start date: " + getStartDateTime() +
                "\nBooking end date: " + getEndDateTime();
    }
}