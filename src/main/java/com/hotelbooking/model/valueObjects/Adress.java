package com.hotelbooking.model.valueObjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Adress
{
    private String country;
    @Column(name = "postal_code")
    private int postalCode;
    private String city;
    private String street;
    @Column(name = "house_number")
    private int houseNumber;

    public Adress()
    {
        country = "";
        postalCode = 0;
        city = "";
        street = "";
        houseNumber = 0;
    }

    public Adress(String country, int postalCode, String city, String street, int houseNumber)
    {
        this.country = country;
        this.postalCode = postalCode;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }
    public String getCountry()
    {
        return country;
    }

    public int getPostalCode()
    {
        return postalCode;
    }

    public String getCity()
    {
        return city;
    }

    public String getStreet()
    {
        return street;
    }

    public int getHouseNumber()
    {
        return houseNumber;
    }
}
