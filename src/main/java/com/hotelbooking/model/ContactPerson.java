package com.hotelbooking.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "contact_person")
public class ContactPerson
{
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "contactPerson", cascade = CascadeType.ALL)
    private List<Activity> activities;

    public ContactPerson()
    {
    }

    public ContactPerson(String firstName, String lastName, String email, String phone)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
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
    public String toString() {
        return "Contact Person information:" +
                "\nUser Id: " + userId +
                "\nFirst Name: " + firstName +
                "\nLast Name: " + lastName +
                "\nMail: " + email +
                "\nPhone: " + phone;
    }
}
