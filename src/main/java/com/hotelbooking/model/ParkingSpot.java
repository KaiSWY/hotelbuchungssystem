package com.hotelbooking.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parking_spot")
public class ParkingSpot
{
    @Id
    @Column(name = "spot_number")
    private int spotNumber;

    @ManyToOne
    @JoinColumn(name = "parking_spot_type_id", nullable = false)
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

    public int getSpotNumber()
    {
        return spotNumber;
    }

    public void setSpotNumber(int spotNumber)
    {
        this.spotNumber = spotNumber;
    }

    public ParkingSpotType getSpotType()
    {
        return spotType;
    }

    public void setSpotType(ParkingSpotType spotType)
    {
        this.spotType = spotType;
    }

    public List<ParkingSpot_User> getParkingSpots_Users()
    {
        return parkingSpots_Users;
    }

    public void setParkingSpots_Users(List<ParkingSpot_User> parkingSpots_Users)
    {
        this.parkingSpots_Users = parkingSpots_Users;
    }

    @Override
    public String toString()
    {
        return "Parking Spot information:" +
                "\nParking Spot number: " + spotNumber +
                "\n" + spotType.toString();
    }
}