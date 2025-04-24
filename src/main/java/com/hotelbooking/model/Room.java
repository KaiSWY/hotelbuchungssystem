package com.hotelbooking.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room")
public class Room
{
    @Id
    @Column(name = "room_number")
    private int roomNumber;
    @Column(name = "double_beds")
    private int doubleBeds;
    @Column(name = "single_beds")
    private int singleBeds;
    private String description;
    @Column(name = "price_per_night")
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

    public int getRoomNumber()
    {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber)
    {
        this.roomNumber = roomNumber;
    }

    public int getDoubleBeds()
    {
        return doubleBeds;
    }

    public void setDoubleBeds(int doubleBeds)
    {
        this.doubleBeds = doubleBeds;
    }

    public int getSingleBeds()
    {
        return singleBeds;
    }

    public void setSingleBeds(int singleBeds)
    {
        this.singleBeds = singleBeds;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getPricePerNight()
    {
        return pricePerNight;
    }

    public void setPricePerNight(int pricePerNight)
    {
        this.pricePerNight = pricePerNight;
    }

    public List<Room_User> getRooms_users()
    {
        return rooms_users;
    }

    public void setRooms_users(List<Room_User> rooms_users)
    {
        this.rooms_users = rooms_users;
    }

    @Override
    public String toString()
    {
        return "Room information:" +
                "\nRoom Number: " + roomNumber +
                "\nDouble beds amount: " + doubleBeds +
                "\nSingle beds amount: " + singleBeds +
                "\nRoom description: " + description +
                "\nPrice per night: " + pricePerNight;
    }
}
