package com.hotelbooking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "activity_user")
public class Activity_User extends Booking
{
    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = false)
    private Activity activity;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Activity_User()
    {
        super();
    }

    public Activity_User(Activity activity, User user, LocalDateTime startDateTime, LocalDateTime endDateTime)
    {
        super(startDateTime, endDateTime);
        this.activity = activity;
        this.user = user;
    }

    public Activity getActivity()
    {
        return activity;
    }

    public void setActivity(Activity activity)
    {
        this.activity = activity;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    @Override
    public String toString()
    {
        return "Activity booking information:" +
                "\n" + activity.toString() +
                "\n" + user.toString() +
                "\nBooking start date: " + getStartDateTime() +
                "\nBooking end date: " + getEndDateTime();
    }
}
