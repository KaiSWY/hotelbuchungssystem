package com.hotelbooking.repository;

import com.hotelbooking.model.RestaurantTable;
import java.util.List;

public interface IRestaurantTableRepository
{
    void add(RestaurantTable restaurantTable);
    void update(RestaurantTable restaurantTable);
    void delete(RestaurantTable restaurantTable);
    RestaurantTable getById(Integer id);
    List<RestaurantTable> getAll();
}
