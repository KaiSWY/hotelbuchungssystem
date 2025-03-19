package com.hotelbooking.repository;

import com.hotelbooking.model.*;
import org.hibernate.Hibernate;
import org.hibernate.Session;

public class EntityLazyLoader
{
    public static <T> void initializeEntity(Session session, T entity)
    {
        if (entity != null)
        {
            switch (entity)
            {
                case User user ->
                {
                    Hibernate.initialize(user.getActivity_users());
                    Hibernate.initialize(user.getRooms_users());
                    Hibernate.initialize(user.getParkingSpots_Users());
                    Hibernate.initialize(user.getTables_users());
                }
                case Activity activity -> Hibernate.initialize(activity.getActivity_users());
                case Activity_User activityUser ->
                {
                    Hibernate.initialize(activityUser.getUser().getActivity_users());
                    Hibernate.initialize(activityUser.getActivity().getActivity_users());
                }
                case Room room -> Hibernate.initialize(room.getRooms_users());
                case Room_User roomUser ->
                {
                    Hibernate.initialize(roomUser.getUser().getRooms_users());
                    Hibernate.initialize(roomUser.getRoom().getRooms_users());
                }
                case ParkingSpot parkingSpot -> Hibernate.initialize(parkingSpot.getParkingSpots_Users());
                case ParkingSpot_User parkingSpotUser ->
                {
                    Hibernate.initialize(parkingSpotUser.getUser().getParkingSpots_Users());
                    Hibernate.initialize(parkingSpotUser.getSpot().getParkingSpots_Users());
                }
                case RestaurantTable restaurantTable -> Hibernate.initialize(restaurantTable.getTables_users());
                case RestaurantTable_User restaurantTableUser ->
                {
                    Hibernate.initialize(restaurantTableUser.getUser().getTables_users());
                    Hibernate.initialize(restaurantTableUser.getTable().getTables_users());
                }
                default ->
                {
                }
            }
        }
    }
}
