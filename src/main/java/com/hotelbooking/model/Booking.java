package com.hotelbooking.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Booking
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "start_date_time")
    private LocalDateTime startDateTime;

    @Column(name = "end_date_time")
    private LocalDateTime endDateTime;

    public Booking()
    {
        startDateTime = LocalDateTime.MIN;
        endDateTime = LocalDateTime.MIN;
    }

    public Booking(LocalDateTime startDateTime, LocalDateTime endDateTime)
    {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    

    public LocalDateTime getStartDateTime()
    {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime)
    {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime()
    {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime)
    {
        this.endDateTime = endDateTime;
    }
}
