package BookingTests;

import Mocks.MockRepository;
import com.hotelbooking.model.Room;
import com.hotelbooking.model.Room_User;
import com.hotelbooking.model.User;
import com.hotelbooking.service.BookingService;
import com.hotelbooking.service.RoomBookingService;

import java.time.LocalDateTime;

public class RoomBookingServiceTest extends AbstractBookingServiceTest<Room_User> {

    private MockRepository<User> userRepository;
    private MockRepository<Room> roomRepository;

    @Override
    protected BookingService<Room_User> createBookingService() {
        userRepository = new MockRepository<>();
        roomRepository = new MockRepository<>();

        User user = new User();
        Room room = new Room();
        room.setRoomNumber(0);

        userRepository.add(user);
        roomRepository.add(room);



        return new RoomBookingService(bookingRepository, userRepository, roomRepository, null);
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
}
