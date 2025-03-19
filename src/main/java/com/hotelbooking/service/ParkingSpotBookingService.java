package com.hotelbooking.service;

import com.hotelbooking.model.Booking;
import com.hotelbooking.model.ParkingSpot;
import com.hotelbooking.model.ParkingSpot_User;
import com.hotelbooking.model.User;
import com.hotelbooking.repository.IRepository;

import java.time.LocalDateTime;
import java.util.List;

public class ParkingSpotBookingService extends BookingService<ParkingSpot_User>
{
    private final IRepository<User, Integer> userRepository;
    private final IRepository<ParkingSpot, Integer> parkingSpotRepository;

    public ParkingSpotBookingService(
            IRepository<ParkingSpot_User, Integer> parkingSpotUserRepository,
            IRepository<User, Integer> userRepository,
            IRepository<ParkingSpot, Integer> parkingSpotRepository)
    {
        super(parkingSpotUserRepository);
        this.userRepository = userRepository;
        this.parkingSpotRepository = parkingSpotRepository;
    }

    @Override
    public void book(Booking booking)
    {
        ParkingSpot_User parkingSpotUserBooking;
        if (booking instanceof ParkingSpot_User)
        {
            parkingSpotUserBooking = (ParkingSpot_User) booking;
        }
        else
        {
            throw new IllegalArgumentException("Invalid booking object");
        }

        User user = parkingSpotUserBooking.getUser();
        ParkingSpot parkingSpot = parkingSpotUserBooking.getSpot();
        LocalDateTime startDateTime = booking.getStartDateTime();
        LocalDateTime endDateTime = booking.getEndDateTime();

        if (!isBookingConflict(parkingSpot.getSpotNumber(), startDateTime, endDateTime))
        {
            ParkingSpot_User parkingSpotUser = new ParkingSpot_User(parkingSpot, user, startDateTime, endDateTime);
            repository.add(parkingSpotUser);
        }
        else
        {
            throw new IllegalArgumentException("ParkingSpot is not available for the selected time period");
        }
    }

    @Override
    public void cancel(Integer bookingId)
    {
        ParkingSpot_User parkingSpotUser = repository.getById(bookingId);
        if (parkingSpotUser == null)
        {
            throw new IllegalArgumentException("Cancellation process failed. Parking spot booking not found with ID: " + bookingId);
        }
        persistDeletedParkingSpotUser(parkingSpotUser);
    }

    @Override
    public List<ParkingSpot_User> getBookingsByEntityId(Integer entityId)
    {
        return parkingSpotRepository.getById(entityId).getParkingSpots_Users();
    }

    private void persistDeletedParkingSpotUser(ParkingSpot_User parkingSpotUser)
    {
        User user = parkingSpotUser.getUser();
        ParkingSpot parkingSpot = parkingSpotUser.getSpot();

        user.getParkingSpots_Users().remove(parkingSpotUser);
        userRepository.update(user);
        parkingSpot.getParkingSpots_Users().remove(parkingSpotUser);
        parkingSpotRepository.update(parkingSpot);
        super.cancel(parkingSpotUser.getId());
    }
}
