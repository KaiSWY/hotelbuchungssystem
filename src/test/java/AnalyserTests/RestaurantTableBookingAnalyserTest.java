package AnalyserTests;

import Mocks.MockRepository;
import com.hotelbooking.model.RestaurantTable;
import com.hotelbooking.model.RestaurantTable_User;
import com.hotelbooking.model.User;
import com.hotelbooking.service.analysers.BookingAnalyser;
import com.hotelbooking.service.analysers.implementations.RestaurantTableBookingAnalyser;

public class RestaurantTableBookingAnalyserTest extends AbstractBookingAnalyserTest<RestaurantTable_User, RestaurantTable> {

    @Override
    protected void setupRepositories() {
        userRepository = new MockRepository<>();
        entityRepository = new MockRepository<>();
        bookingRepository = new MockRepository<>();
    }

    @Override
    protected BookingAnalyser<RestaurantTable_User, RestaurantTable, Integer> setupBookingAnalyser() {
        return new RestaurantTableBookingAnalyser(entityRepository, bookingRepository);
    }

    @Override
    protected RestaurantTable_User createEntity() {
        RestaurantTable restaurantTable = new RestaurantTable();
        restaurantTable.setTableNumber(5);

        User user = new User();
        user.setUserId(0);
        user.setEmail("user@restaurant.com");

        RestaurantTable_User restaurantTableUser = new RestaurantTable_User();
        restaurantTableUser.setTable(restaurantTable);
        restaurantTableUser.setUser(user);
        restaurantTableUser.setStartDateTime(START_TIME);
        restaurantTableUser.setEndDateTime(END_TIME);

        return restaurantTableUser;
    }

    @Override
    protected void addDataToRepositories(RestaurantTable_User restaurantTableUser) {
        User user = restaurantTableUser.getUser();
        userRepository.add(user);
        user.getTables_users().add(restaurantTableUser);

        RestaurantTable restaurantTable = restaurantTableUser.getTable();
        entityRepository.add(restaurantTable);
        bookingRepository.add(restaurantTableUser);
        restaurantTable.getTables_users().add(restaurantTableUser);
    }
}
