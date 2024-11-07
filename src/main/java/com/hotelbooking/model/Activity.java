package com.hotelbooking.model;

import jakarta.persistence.*;

@Entity
@Table(name = "activity")
public class Activity
{
    @Id
    @Column(name = "activity_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int activityId;
    private String name;
    private String description;
    @Column(name = "price_per_person")
    private double pricePerPerson;
    @Column(name = "max_participants")
    private int maxParticipants;

    public Activity(int activityNumber, String name, String description, double pricePerPerson, int maxParticipants)
    {
        this.activityId = activityNumber;
        this.name = name;
        this.description = description;
        this.pricePerPerson = pricePerPerson;
        this.maxParticipants = maxParticipants;
    }

    public Activity()
    {
    }

    public int getActivityId()
    {
        return activityId;
    }

    public void setActivityId(int activityId)
    {
        this.activityId = activityId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public double getPricePerPerson()
    {
        return pricePerPerson;
    }

    public void setPricePerPerson(double pricePerPerson)
    {
        this.pricePerPerson = pricePerPerson;
    }

    public int getMaxParticipants()
    {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants)
    {
        this.maxParticipants = maxParticipants;
    }
}
