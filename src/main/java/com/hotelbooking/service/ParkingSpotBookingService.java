package com.hotelbooking.service;

import com.hotelbooking.model.*;
import com.hotelbooking.repository.IParkingSpotRepository;
import com.hotelbooking.repository.IUserRepository;
import java.time.LocalDateTime;
import java.util.List;

public class ParkingSpotBookingService
{
    private final IUserRepository userRepository;
    private final IParkingSpotRepository parkingSpotRepository;

    public ParkingSpotBookingService(IUserRepository userRepository, IParkingSpotRepository parkingSpotRepository) {
        this.userRepository = userRepository;
        this.parkingSpotRepository = parkingSpotRepository;
    }

    //TODO error handling
    public void book(int userId, int parkingSpotId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        User user = userRepository.getById(userId);
        ParkingSpot parkingSpot = parkingSpotRepository.getById(parkingSpotId);

        if (isBookingConflict(parkingSpot, startDateTime, endDateTime)) {
            ParkingSpot_User parkingSpotUser = new ParkingSpot_User(parkingSpot, user, startDateTime, endDateTime);
            user.parkingSpots_Users().add(parkingSpotUser);
            userRepository.update(user);
        } else {
            throw new IllegalArgumentException("ParkingSpot is not available for the selected time period");
        }
    }

    private boolean isBookingConflict(ParkingSpot parkingSpot, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        List<ParkingSpot_User> conflictingBookings = parkingSpotRepository.findConflictingBookings(parkingSpot, startDateTime, endDateTime);
        return conflictingBookings.isEmpty();
    }
}
