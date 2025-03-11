package com.hotelbooking.service;

import com.hotelbooking.model.*;
import com.hotelbooking.repository.*;
import java.time.LocalDateTime;
import java.util.List;

public class RoomBookingService extends BookingService<Integer>
{
    private final IUserRepository userRepository;
    private final IRoomRepository roomRepository;

    public RoomBookingService(IUserRepository userRepository, IRoomRepository roomRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    //TODO error handling
    @Override
    public void book(Booking booking) {
        Room_User roomUserBooking;
        if(booking instanceof Room_User) {
            roomUserBooking = (Room_User) booking;
        } else {
            // TODO Required error handling for wrong booking object
            throw new IllegalArgumentException("Invalid booking object");
        }

        User user = userRepository.getById(roomUserBooking.getUser().getUserId());
                //.orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        Room room = roomRepository.getById(roomUserBooking.getRoom().getRoomNumber());
                //.orElseThrow(() -> new IllegalArgumentException("Room not found with id: " + roomId));
        LocalDateTime startDateTime = booking.getStartDateTime();
        LocalDateTime endDateTime = booking.getEndDateTime();

        if (!isBookingConflict(room.getRoomNumber(), startDateTime, endDateTime)) {
            Room_User roomUser = new Room_User(room, user, startDateTime, endDateTime);
            user.getRooms_users().add(roomUser);
            userRepository.update(user);
        } else {
            throw new IllegalArgumentException("Room is not available for the selected time period");
        }
    }

    @Override
    public void cancel()
    {

    }

    @Override
    protected List<Booking> getBookingsFromEntity(Integer entityId)
    {
        return roomRepository.getById(entityId).getRooms_users().stream().map(x -> (Booking) x).toList();
    }
}
