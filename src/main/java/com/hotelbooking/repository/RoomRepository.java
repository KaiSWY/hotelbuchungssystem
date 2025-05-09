package com.hotelbooking.repository;

import com.hotelbooking.model.Room;
import org.hibernate.SessionFactory;

public class RoomRepository extends Repository<Room, Integer>
{
    public RoomRepository(SessionFactory sessionFactory)
    {
        super(sessionFactory, Room.class);
    }
}
