package com.hotelbooking.model;

import com.hotelbooking.model.valueObjects.Adress;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
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
    @Column(unique = true, nullable = false)
    private String email;
    private Adress adress;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ParkingSpot_User> parkingSpots_Users = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Room_User> rooms_users = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<RestaurantTable_User> tables_users = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Activity_User> activity_users = new ArrayList<>();

    public User()
    {
    }

    public User(String firstName, String lastName, String email, String country, int postalCode, String city, String street, int houseNumber)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.adress = new Adress(country, postalCode, city, street, houseNumber);
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

    public Adress getAdress()
    {
        return adress;
    }

    public void setAdress(Adress adress)
    {
        this.adress = adress;
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

    public List<Activity_User> getActivity_users()
    {
        return activity_users;
    }

    public void setActivity_users(List<Activity_User> activity_users)
    {
        this.activity_users = activity_users;
    }


    @Override
    public String toString()
    {
        return "User information:" +
                "\nUser ID: " + userId +
                "\nName: " + firstName + " " + lastName +
                "\nMail: " + email +
                "\nCountry: " + adress.getCountry() +
                "\nPostal Code & City: " + adress.getPostalCode() + " " + adress.getCity() +
                "\nStreet & House Number: " + adress.getStreet() + " " + adress.getHouseNumber();
    }
}
