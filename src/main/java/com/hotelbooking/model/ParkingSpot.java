package com.hotelbooking.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parking_spot")
public class ParkingSpot
{
    @Id
    private int spotNumber;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private ParkingSpotType spotType;

    @OneToMany(mappedBy = "spot")
    private List<ParkingSpot_User> parkingSpots_Users = new ArrayList<>();

    public ParkingSpot()
    {
    }

    public ParkingSpot(int spotNumber, ParkingSpotType spotType)
    {
        this.spotNumber = spotNumber;
        this.spotType = spotType;
    }

    public int spotNumber()
    {
        return spotNumber;
    }

    public void spotNumber(int spotNumber)
    {
        this.spotNumber = spotNumber;
    }

    public ParkingSpotType spotType()
    {
        return spotType;
    }

    public void spotType(ParkingSpotType spotType)
    {
        this.spotType = spotType;
    }

    public List<ParkingSpot_User> parkingSpots_Users()
    {
        return parkingSpots_Users;
    }

    public void parkingSpots_Users(List<ParkingSpot_User> parkingSpots_Users)
    {
        this.parkingSpots_Users = parkingSpots_Users;
    }
}