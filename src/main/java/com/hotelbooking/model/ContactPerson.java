package com.hotelbooking.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "contact_person")
public class ContactPerson extends Person
{
    private String phone;

    @OneToMany(mappedBy = "contactPerson", cascade = CascadeType.ALL)
    private List<Activity> activities;

    public ContactPerson() {}

    public ContactPerson(String firstName, String lastName, String email, String phone)
    {
        super(firstName, lastName, email);
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public List<Activity> getActivities()
    {
        return activities;
    }

    public void setActivities(List<Activity> activities)
    {
        this.activities = activities;
    }

    @Override
    public String toString()
    {
        return "Contact Person information:" +
                "\nUser Id: " + getUserId() +
                "\nFirst Name: " + getFirstName() +
                "\nLast Name: " + getLastName() +
                "\nMail: " + getEmail() +
                "\nPhone: " + phone;
    }
}
