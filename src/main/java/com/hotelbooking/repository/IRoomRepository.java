package com.hotelbooking.repository;

import com.hotelbooking.model.Room;
import java.util.List;

public interface IRoomRepository
{
    void add(Room room);
    void update(Room room);
    void delete(Room room);
    Room getById(Integer id);
    List<Room> getAll();
}
