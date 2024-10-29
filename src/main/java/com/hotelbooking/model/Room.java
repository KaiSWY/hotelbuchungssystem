package com.hotelbooking.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room")
public class Room
{
    @Id
    private int roomNumber;
    private int doubleBeds;
    private int singleBeds;
    private String description;
    private int pricePerNight;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Room_User> rooms_users = new ArrayList<>();

    public Room(int roomNumber, int doubleBeds, int singleBeds, String description, int pricePerNight)
    {
        this.roomNumber = roomNumber;
        this.doubleBeds = doubleBeds;
        this.singleBeds = singleBeds;
        this.description = description;
        this.pricePerNight = pricePerNight;
    }

    public Room()
    {
    }

    public int roomNumber()
    {
        return roomNumber;
    }

    public void roomNumber(int roomNumber)
    {
        this.roomNumber = roomNumber;
    }

    public int doubleBeds()
    {
        return doubleBeds;
    }

    public void doubleBeds(int doubleBeds)
    {
        this.doubleBeds = doubleBeds;
    }

    public int singleBeds()
    {
        return singleBeds;
    }

    public void singleBeds(int singleBeds)
    {
        this.singleBeds = singleBeds;
    }

    public String description()
    {
        return description;
    }

    public void description(String description)
    {
        this.description = description;
    }

    public int pricePerNight()
    {
        return pricePerNight;
    }

    public void pricePerNight(int pricePerNight)
    {
        this.pricePerNight = pricePerNight;
    }

    public List<Room_User> rooms_users()
    {
        return rooms_users;
    }

    public void rooms_users(List<Room_User> rooms_users)
    {
        this.rooms_users = rooms_users;
    }
}
