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

    public int activityId()
    {
        return activityId;
    }

    public void activityId(int activityId)
    {
        this.activityId = activityId;
    }

    public String name()
    {
        return name;
    }

    public void name(String name)
    {
        this.name = name;
    }

    public String description()
    {
        return description;
    }

    public void description(String description)
    {
        this.description = description;
    }

    public double pricePerPerson()
    {
        return pricePerPerson;
    }

    public void pricePerPerson(int pricePerPerson)
    {
        this.pricePerPerson = pricePerPerson;
    }

    public int maxParticipants()
    {
        return maxParticipants;
    }

    public void maxParticipants(int maxParticipants)
    {
        this.maxParticipants = maxParticipants;
    }
}
