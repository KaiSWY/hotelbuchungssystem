package com.hotelbooking.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant_table")
public class RestaurantTable
{
    @Id
    @Column(name = "table_number")
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

    public int getTableNumber()
    {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber)
    {
        this.tableNumber = tableNumber;
    }

    public int getSeats()
    {
        return seats;
    }

    public void setSeats(int seats)
    {
        this.seats = seats;
    }

    public List<RestaurantTable_User> getTables_users()
    {
        return tables_users;
    }

    public void setTables_users(List<RestaurantTable_User> tables_users)
    {
        this.tables_users = tables_users;
    }

    @Override
    public String toString() {
        return "Restaurant Table information:" +
                "\nTable number: " + tableNumber +
                "\nSeats: " + seats;
    }
}
