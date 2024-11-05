package com.hotelbooking.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User
{
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    @Column(name = "postal_code")
    private int postalCode;
    private String city;
    private String street;
    @Column(name = "house_number")
    private int houseNumber;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ParkingSpot_User> parkingSpots_Users = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Room_User> rooms_users = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<RestaurantTable_User> tables_users = new ArrayList<>();

    public User()
    {
    }

    public User(String firstName, String lastName, String email, int postalCode, String city, String street, int houseNumber)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.postalCode = postalCode;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public int userId()
    {
        return userId;
    }

    public void userId(int userId)
    {
        this.userId = userId;
    }

    public String firstName()
    {
        return firstName;
    }

    public void firstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String lastName()
    {
        return lastName;
    }

    public void lastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String email()
    {
        return email;
    }

    public void email(String email)
    {
        this.email = email;
    }

    public int postalCode()
    {
        return postalCode;
    }

    public void postalCode(int postalCode)
    {
        this.postalCode = postalCode;
    }

    public String city()
    {
        return city;
    }

    public void city(String city)
    {
        this.city = city;
    }

    public String street()
    {
        return street;
    }

    public void street(String street)
    {
        this.street = street;
    }

    public int houseNumber()
    {
        return houseNumber;
    }

    public void houseNumber(int houseNumber)
    {
        this.houseNumber = houseNumber;
    }

    public List<ParkingSpot_User> parkingSpots_Users()
    {
        return parkingSpots_Users;
    }

    public void parkingSpots_Users(List<ParkingSpot_User> parkingSpots_Users)
    {
        this.parkingSpots_Users = parkingSpots_Users;
    }

    public List<Room_User> rooms_users()
    {
        return rooms_users;
    }

    public void rooms_users(List<Room_User> rooms_users)
    {
        this.rooms_users = rooms_users;
    }

    public List<RestaurantTable_User> tables_users()
    {
        return tables_users;
    }

    public void tables_users(List<RestaurantTable_User> tables_users)
    {
        this.tables_users = tables_users;
    }
}
