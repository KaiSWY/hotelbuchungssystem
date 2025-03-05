package com.hotelbooking.service;

import com.hotelbooking.model.*;
import com.hotelbooking.repository.*;
import java.time.LocalDateTime;
import java.util.List;

public class RoomBookingService
{
    private final IUserRepository userRepository;
    private final IRoomRepository roomRepository;

    public RoomBookingService(IUserRepository userRepository, IRoomRepository roomRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    //TODO error handling
    public void book(int userId, int roomId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        User user = userRepository.getById(userId);
                //.orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        Room room = roomRepository.getById(roomId);
                //.orElseThrow(() -> new IllegalArgumentException("Room not found with id: " + roomId));

        if (isBookingConflict(room, startDateTime, endDateTime)) {
            Room_User roomUser = new Room_User(room, user, startDateTime, endDateTime);
            user.rooms_users().add(roomUser);
            userRepository.update(user);
        } else {
            throw new IllegalArgumentException("Room is not available for the selected time period");
        }
    }

    private boolean isBookingConflict(Room room, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        List<Room_User> conflictingBookings = roomRepository.findConflictingBookings(room, startDateTime, endDateTime);
        return conflictingBookings.isEmpty();
    }
}
