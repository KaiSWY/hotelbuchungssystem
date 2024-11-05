package com.hotelbooking.model;

import jakarta.persistence.*;

@Entity
@Table(name = "activity_user")
public class Activity_User
{
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = false)
    private Activity activity;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //TODO logic for general activities - timestamps in activities as well? -- structure from activities?
}
