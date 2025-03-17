package com.hotelbooking.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "activity")
public class Activity
{
    @Id
    @Column(name = "activity_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int activityId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(name = "price_per_person", nullable = false)
    private double pricePerPerson;
    @Column(name = "max_participants", nullable = false)
    private int maxParticipants;
    @Column(nullable = false)
    private String place;
    @ManyToOne
    @JoinColumn(name = "contact_person_id")
    private ContactPerson contactPerson;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Activity_User> activity_users = new ArrayList<>();

    public Activity(int activityNumber, String name, String description, double pricePerPerson, int maxParticipants, String place, ContactPerson contactPerson)
    {
        this.activityId = activityNumber;
        this.name = name;
        this.description = description;
        this.pricePerPerson = pricePerPerson;
        this.maxParticipants = maxParticipants;
        this.place = place;
        this.contactPerson = contactPerson;
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

    public String getPlace()
    {
        return place;
    }

    public void setPlace(String place)
    {
        this.place = place;
    }

    public ContactPerson getContactPerson()
    {
        return contactPerson;
    }

    public void setContactPerson(ContactPerson contactPerson)
    {
        this.contactPerson = contactPerson;
    }

    public List<Activity_User> getActivity_users()
    {
        return activity_users;
    }

    public void setActivity_users(List<Activity_User> activity_users)
    {
        this.activity_users = activity_users;
    }
}
