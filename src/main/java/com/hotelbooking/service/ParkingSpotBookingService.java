package com.hotelbooking.service;

import com.hotelbooking.model.*;
import com.hotelbooking.repository.IParkingSpotRepository;
import com.hotelbooking.repository.IUserRepository;

import java.time.LocalDateTime;
import java.util.List;

public class ParkingSpotBookingService extends BookingService<Integer>
{
    private final IUserRepository userRepository;
    private final IParkingSpotRepository parkingSpotRepository;

    public ParkingSpotBookingService(IUserRepository userRepository, IParkingSpotRepository parkingSpotRepository) {
        this.userRepository = userRepository;
        this.parkingSpotRepository = parkingSpotRepository;
    }

    //TODO error handling
    @Override
    public void book(Booking booking) {
        ParkingSpot_User parkingSpotUserBooking;
        if(booking instanceof ParkingSpot_User) {
            parkingSpotUserBooking = (ParkingSpot_User) booking;
        } else {
            // TODO Required error handling for wrong booking object
            throw new IllegalArgumentException("Invalid booking object");
        }

        User user = userRepository.getById(parkingSpotUserBooking.getUser().getUserId());
        ParkingSpot parkingSpot = parkingSpotRepository.getById(parkingSpotUserBooking.getSpot().getSpotNumber());
        LocalDateTime startDateTime = booking.getStartDateTime();
        LocalDateTime endDateTime = booking.getEndDateTime();

        if (!isBookingConflict(parkingSpot.getSpotNumber(), startDateTime, endDateTime)) {
            ParkingSpot_User parkingSpotUser = new ParkingSpot_User(parkingSpot, user, startDateTime, endDateTime);
            user.getParkingSpots_Users().add(parkingSpotUser);
            userRepository.update(user);
        } else {
            throw new IllegalArgumentException("ParkingSpot is not available for the selected time period");
        }
    }

    @Override
    public void cancel()
    {

    }

    @Override
    protected List<Booking> getBookingsFromEntity(Integer entityId)
    {
        return parkingSpotRepository.getById(entityId).getParkingSpots_Users().stream().map(x -> (Booking) x).toList();
    }
}
