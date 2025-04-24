package BookingTests;

import Mocks.MockParkingSpotBookingService;
import Mocks.MockRepository;
import com.hotelbooking.model.*;
import com.hotelbooking.service.BookingService;
import com.hotelbooking.service.RoomBookingService;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoomBookingServiceTest extends AbstractBookingServiceTest<Room_User> {

    private MockRepository<User> userRepository;
    private MockRepository<Room> roomRepository;
    private RoomBookingService roomBookingService;

    @Override
    protected BookingService<Room_User> createBookingService() {
        userRepository = new MockRepository<>();
        roomRepository = new MockRepository<>();

        User user = new User();
        Room room = new Room();
        room.setRoomNumber(0);

        userRepository.add(user);
        roomRepository.add(room);

        roomBookingService = new RoomBookingService(
                bookingRepository,
                userRepository,
                roomRepository,
                new MockParkingSpotBookingService()
        );

        return roomBookingService;
    }

    @Override
    protected Room_User createValidBooking() {
        User user = userRepository.getAll().getFirst();
        Room room = roomRepository.getAll().getFirst();
        Room_User roomUser = new Room_User(room, user, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2));
        user.getRooms_users().add(roomUser);
        return roomUser;
    }

    @Override
    protected Room_User createConflictingBooking() {
        Room_User existing = createValidBooking();
        existing.getRoom().getRooms_users().add(existing);
        return new Room_User(
                existing.getRoom(),
                existing.getUser(),
                existing.getStartDateTime().plusHours(1),
                existing.getEndDateTime().plusHours(1)
        );
    }

    @Test
    void testBookingWithParkingSpot() {
        Room_User roomUser_booking = createValidBooking();

        roomBookingService.book(roomUser_booking, getParkingSpot_User(roomUser_booking.getUser()));

        List<Room_User> allBookings = bookingRepository.getAll();
        assertEquals(1, allBookings.size());
        assertEquals(0, allBookings.getFirst().getId());
    }

    @Test
    void testBookingConflictWithParkingSpot() {
        Room_User conflict = createConflictingBooking();
        assertThrows(IllegalArgumentException.class, () -> roomBookingService.book(conflict, getParkingSpot_User(conflict.getUser())));
    }

    private ParkingSpot_User getParkingSpot_User(User user){
        ParkingSpot_User parkingSpotUser_booking = new ParkingSpot_User();
        parkingSpotUser_booking.setUser(user);
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setSpotNumber(0);
        parkingSpotUser_booking.setSpot(parkingSpot);
        return parkingSpotUser_booking;
    }
}
