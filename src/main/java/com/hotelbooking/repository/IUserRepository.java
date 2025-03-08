package com.hotelbooking.repository;

import com.hotelbooking.model.User;
import java.util.List;

public interface IUserRepository
{
    void add(User user);
    void update(User user);
    void delete(User user);
    User getById(Integer id);
    List<User> getAll();
}
