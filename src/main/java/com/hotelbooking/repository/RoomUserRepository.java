package com.hotelbooking.repository;

import com.hotelbooking.model.Room_User;
import org.hibernate.SessionFactory;

public class RoomUserRepository extends Repository<Room_User, Integer>
{
    public RoomUserRepository(SessionFactory sessionFactory)
    {
        super(sessionFactory, Room_User.class);
    }
}
