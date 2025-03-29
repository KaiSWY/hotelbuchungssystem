package com.hotelbooking.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parking_spot_type")
public class ParkingSpotType
{
    @Id
    @Column(name = "spot_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int typeId;
    @Column(name = "name")
    private String typeName; // e.g. "Underground", "Open Air", "Standard"
    @Column(name = "price_per_hour")
    private double pricePerHour;

    @OneToMany(mappedBy = "spotType", cascade = CascadeType.ALL)
    private List<ParkingSpot> spots = new ArrayList<>();

    public ParkingSpotType(String typeName, double pricePerHour)
    {
        this.typeName = typeName;
        this.pricePerHour = pricePerHour;
    }

    public ParkingSpotType()
    {
    }

    public int getTypeId()
    {
        return typeId;
    }

    public void setTypeId(int typeId)
    {
        this.typeId = typeId;
    }

    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    public double getPricePerHour()
    {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour)
    {
        this.pricePerHour = pricePerHour;
    }

    public List<ParkingSpot> getSpots()
    {
        return spots;
    }

    public void setSpots(List<ParkingSpot> spots)
    {
        this.spots = spots;
    }

    @Override
    public String toString() {
        return "Parking Spot type information:" +
                "\nParking spot type Id: " + typeId +
                "\nType name: " + typeName +
                "\nPrice per hour: " + pricePerHour;
    }
}
