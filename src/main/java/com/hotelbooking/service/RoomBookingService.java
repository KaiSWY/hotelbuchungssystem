package com.hotelbooking.service;

import com.hotelbooking.model.*;
import com.hotelbooking.repository.IRepository;

import java.time.LocalDateTime;
import java.util.List;

public class RoomBookingService extends BookingService<Room_User>
{
    private final IRepository<User, Integer> userRepository;
    private final IRepository<Room, Integer> roomRepository;
    private final BookingService<ParkingSpot_User> parkingSpotBookingService;

    public RoomBookingService(
            IRepository<Room_User, Integer> roomUserRepository,
            IRepository<User, Integer> userRepository,
            IRepository<Room, Integer> roomRepository,
            BookingService<ParkingSpot_User> parkingSpotBookingService)
    {
        super(roomUserRepository);
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.parkingSpotBookingService = parkingSpotBookingService;
    }

    @Override
    public void book(Booking booking)
    {
        Room_User roomUserBooking = getRoomUserBookingObject(booking);

        User user = userRepository.getById(roomUserBooking.getUser().getUserId());
        //.orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        Room room = roomRepository.getById(roomUserBooking.getRoom().getRoomNumber());
        //.orElseThrow(() -> new IllegalArgumentException("Room not found with id: " + roomId));
        LocalDateTime startDateTime = booking.getStartDateTime();
        LocalDateTime endDateTime = booking.getEndDateTime();

        if (!isBookingConflict(room.getRoomNumber(), startDateTime, endDateTime))
        {
            Room_User roomUser = new Room_User(room, user, startDateTime, endDateTime);
            repository.add(roomUser);
        }
        else
        {
            throw new IllegalArgumentException("Room is not available for the selected time period");
        }
    }

    public void book(Booking booking, ParkingSpot_User parkingSpotUser)
    {
        Room_User roomUserBooking = getRoomUserBookingObject(booking);

        ParkingSpot parkingSpot = parkingSpotUser.getSpot();
        int parkingSpotNumber = parkingSpot.getSpotNumber();
        Room room = roomRepository.getById(roomUserBooking.getRoom().getRoomNumber());
        int roomNumber = room.getRoomNumber();
        LocalDateTime startDateTime = booking.getStartDateTime();
        LocalDateTime endDateTime = booking.getEndDateTime();

        if (isBookingConflict(roomNumber, startDateTime, endDateTime))
        {
            throw new IllegalArgumentException("Room is not available for the selected time period");
        }
        if (parkingSpotBookingService.isBookingConflict(parkingSpotNumber, startDateTime, endDateTime))
        {
            throw new IllegalArgumentException("Parking spot " + parkingSpotNumber + " is not available for the selected time period");
        }
        book(booking);
        parkingSpotBookingService.book(parkingSpotUser);
    }

    private Room_User getRoomUserBookingObject(Booking booking)
    {
        if (booking instanceof Room_User)
        {
            return (Room_User) booking;
        }
        else
        {
            throw new IllegalArgumentException("Invalid booking object");
        }
    }

    @Override
    public void cancel(Integer bookingId)
    {
        Room_User roomUser = repository.getById(bookingId);
        if (roomUser == null)
        {
            throw new IllegalArgumentException("Cancellation process failed. Room booking not found with ID: " + bookingId);
        }
        persistDeleteRoomUser(roomUser);
    }

    @Override
    public List<Room_User> getBookingsByEntityId(Integer entityId)
    {
        return roomRepository.getById(entityId).getRooms_users();
    }

    public void persistDeleteRoomUser(Room_User roomUser)
    {
        User user = roomUser.getUser();
        Room room = roomUser.getRoom();

        user.getRooms_users().remove(roomUser);
        userRepository.update(user);
        room.getRooms_users().remove(roomUser);
        roomRepository.update(room);
        super.cancel(roomUser.getId());
    }
}
