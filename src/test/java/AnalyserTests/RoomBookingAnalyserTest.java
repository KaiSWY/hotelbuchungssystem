package AnalyserTests;

import Mocks.MockRepository;
import com.hotelbooking.model.Room;
import com.hotelbooking.model.Room_User;
import com.hotelbooking.model.User;
import com.hotelbooking.service.analysers.BookingAnalyser;
import com.hotelbooking.service.analysers.implementations.RoomBookingAnalyser;

import java.time.LocalDateTime;

public class RoomBookingAnalyserTest extends AbstractBookingAnalyserTest<Room_User, Room> {

    @Override
    protected void setupRepositories() {
        userRepository = new MockRepository<>();
        entityRepository = new MockRepository<>();
        bookingRepository = new MockRepository<>();
    }

    @Override
    protected BookingAnalyser<Room_User, Room, Integer> setupBookingAnalyser() {
        return new RoomBookingAnalyser(entityRepository, bookingRepository);
    }

    @Override
    protected Room_User createEntity() {
        Room room = new Room();
        room.setRoomNumber(101);

        User user = new User();
        user.setUserId(0);
        user.setEmail("user@room.com");

        Room_User roomUser = new Room_User();
        roomUser.setRoom(room);
        roomUser.setUser(user);
        roomUser.setStartDateTime(START_TIME);
        roomUser.setEndDateTime(END_TIME);

        return roomUser;
    }

    @Override
    protected void addDataToRepositories(Room_User roomUser) {
        User user = roomUser.getUser();
        userRepository.add(user);
        user.getRooms_users().add(roomUser);

        Room room = roomUser.getRoom();
        entityRepository.add(room);
        bookingRepository.add(roomUser);
        room.getRooms_users().add(roomUser);
    }
}
