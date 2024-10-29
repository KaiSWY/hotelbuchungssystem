package com.hotelbooking.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant_table")
public class RestaurantTable
{
    @Id
    private int tableNumber;
    private int seats;

    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL)
    private List<RestaurantTable_User> tables_users = new ArrayList<>();

    public RestaurantTable(int tableNumber, int seats)
    {
        this.tableNumber = tableNumber;
        this.seats = seats;
    }

    public RestaurantTable()
    {
    }

    public int tableNumber()
    {
        return tableNumber;
    }

    public void tableNumber(int tableNumber)
    {
        this.tableNumber = tableNumber;
    }

    public int seats()
    {
        return seats;
    }

    public void seats(int seats)
    {
        this.seats = seats;
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
