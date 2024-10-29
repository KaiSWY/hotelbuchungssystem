package com.hotelbooking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parking_spot_type")
public class ParkingSpotType
{
    @Id
    private int typeId;
    private String typeName; // e.g. "Underground", "Open Air", "Standard"
    private double pricePerHour;

    @OneToMany(mappedBy = "spot_type")
    private List<ParkingSpot> spots = new ArrayList<>();

    public ParkingSpotType(int typeId, String typeName, double pricePerHour)
    {
        this.typeId = typeId;
        this.typeName = typeName;
        this.pricePerHour = pricePerHour;
    }

    public ParkingSpotType()
    {
    }

    public int typeId()
    {
        return typeId;
    }

    public void typeId(int typeId)
    {
        this.typeId = typeId;
    }

    public String typeName()
    {
        return typeName;
    }

    public void typeName(String typeName)
    {
        this.typeName = typeName;
    }

    public double pricePerHour()
    {
        return pricePerHour;
    }

    public void pricePerHour(double pricePerHour)
    {
        this.pricePerHour = pricePerHour;
    }

    public List<ParkingSpot> spots()
    {
        return spots;
    }

    public void spots(List<ParkingSpot> spots)
    {
        this.spots = spots;
    }
}
