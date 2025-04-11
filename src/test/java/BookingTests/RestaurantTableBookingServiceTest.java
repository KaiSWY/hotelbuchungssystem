package BookingTests;

import Mocks.MockRepository;
import com.hotelbooking.model.RestaurantTable;
import com.hotelbooking.model.RestaurantTable_User;
import com.hotelbooking.model.User;
import com.hotelbooking.service.BookingService;
import com.hotelbooking.service.RestaurantTableBookingService;

import java.time.LocalDateTime;

public class RestaurantTableBookingServiceTest extends AbstractBookingServiceTest<RestaurantTable_User> {

    private MockRepository<User> userRepository;
    private MockRepository<RestaurantTable> restaurantTableRepository;

    @Override
    protected BookingService<RestaurantTable_User> createBookingService() {
        userRepository = new MockRepository<>();
        restaurantTableRepository = new MockRepository<>();

        User user = new User();
        RestaurantTable restaurantTable = new RestaurantTable();
        restaurantTable.setTableNumber(0);

        userRepository.add(user);
        restaurantTableRepository.add(restaurantTable);

        return new RestaurantTableBookingService(bookingRepository, userRepository, restaurantTableRepository);
    }

    @Override
    protected RestaurantTable_User createValidBooking() {
        User user = userRepository.getAll().getFirst();
        RestaurantTable restaurantTable = restaurantTableRepository.getAll().getFirst();
        RestaurantTable_User tableUser = new RestaurantTable_User(restaurantTable, user, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2));
        user.getTables_users().add(tableUser);
        return tableUser;
    }

    @Override
    protected RestaurantTable_User createConflictingBooking() {
        RestaurantTable_User existing = createValidBooking();
        existing.getTable().getTables_users().add(existing);
        return new RestaurantTable_User(
                existing.getTable(),
                existing.getUser(),
                existing.getStartDateTime().plusHours(1),
                existing.getEndDateTime().plusHours(1)
        );
    }
}
