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

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(int postalCode)
    {
        this.postalCode = postalCode;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public int getHouseNumber()
    {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber)
    {
        this.houseNumber = houseNumber;
    }

    public List<ParkingSpot_User> getParkingSpots_Users()
    {
        return parkingSpots_Users;
    }

    public void setParkingSpots_Users(List<ParkingSpot_User> parkingSpots_Users)
    {
        this.parkingSpots_Users = parkingSpots_Users;
    }

    public List<Room_User> getRooms_users()
    {
        return rooms_users;
    }

    public void setRooms_users(List<Room_User> rooms_users)
    {
        this.rooms_users = rooms_users;
    }

    public List<RestaurantTable_User> getTables_users()
    {
        return tables_users;
    }

    public void setTables_users(List<RestaurantTable_User> tables_users)
    {
        this.tables_users = tables_users;
    }
}
