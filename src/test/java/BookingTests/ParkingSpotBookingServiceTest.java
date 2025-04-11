package BookingTests;

import Mocks.MockRepository;
import com.hotelbooking.model.ParkingSpot;
import com.hotelbooking.model.ParkingSpot_User;
import com.hotelbooking.model.User;
import com.hotelbooking.service.BookingService;
import com.hotelbooking.service.ParkingSpotBookingService;

import java.time.LocalDateTime;

public class ParkingSpotBookingServiceTest extends AbstractBookingServiceTest<ParkingSpot_User> {

    private MockRepository<User> userRepository;
    private MockRepository<ParkingSpot> parkingSpotRepository;

    @Override
    protected BookingService<ParkingSpot_User> createBookingService() {
        userRepository = new MockRepository<>();
        parkingSpotRepository = new MockRepository<>();

        User user = new User();
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setSpotNumber(0);

        userRepository.add(user);
        parkingSpotRepository.add(parkingSpot);

        return new ParkingSpotBookingService(bookingRepository, userRepository, parkingSpotRepository);
    }

    @Override
    protected ParkingSpot_User createValidBooking() {
        User user = userRepository.getAll().getFirst();
        ParkingSpot parkingSpot = parkingSpotRepository.getAll().getFirst();
        ParkingSpot_User parkingSpotUser = new ParkingSpot_User(parkingSpot, user, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2));
        user.getParkingSpots_Users().add(parkingSpotUser);
        return parkingSpotUser;
    }

    @Override
    protected ParkingSpot_User createConflictingBooking() {
        ParkingSpot_User existing = createValidBooking();
        existing.getSpot().getParkingSpots_Users().add(existing);
        return new ParkingSpot_User(
                existing.getSpot(),
                existing.getUser(),
                existing.getStartDateTime().plusHours(1),
                existing.getEndDateTime().plusHours(1)
        );
    }
}