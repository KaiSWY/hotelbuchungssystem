package AnalyserTests;

import Mocks.MockRepository;
import com.hotelbooking.model.ParkingSpot;
import com.hotelbooking.model.ParkingSpot_User;
import com.hotelbooking.model.User;
import com.hotelbooking.service.analysers.BookingAnalyser;
import com.hotelbooking.service.analysers.implementations.ParkingSpotBookingAnalyser;

import java.time.LocalDateTime;

public class ParkingSpotBookingAnalyserTest extends AbstractBookingAnalyserTest<ParkingSpot_User, ParkingSpot> {

    @Override
    protected void setupRepositories() {
        userRepository = new MockRepository<>();
        entityRepository = new MockRepository<>();
        bookingRepository = new MockRepository<>();
    }

    @Override
    protected BookingAnalyser<ParkingSpot_User, ParkingSpot, Integer> setupBookingAnalyser() {
        return new ParkingSpotBookingAnalyser(entityRepository, bookingRepository);
    }

    @Override
    protected ParkingSpot_User createEntity() {
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setSpotNumber(101);

        User user = new User();
        user.setUserId(0);
        user.setEmail("user@parking.com");

        ParkingSpot_User parkingSpotUser = new ParkingSpot_User();
        parkingSpotUser.setSpot(parkingSpot);
        parkingSpotUser.setUser(user);
        parkingSpotUser.setStartDateTime(START_TIME);
        parkingSpotUser.setEndDateTime(END_TIME);

        return parkingSpotUser;
    }

    @Override
    protected void addDataToRepositories(ParkingSpot_User parkingSpotUser) {
        User user = parkingSpotUser.getUser();
        userRepository.add(user);
        user.getParkingSpots_Users().add(parkingSpotUser);

        ParkingSpot parkingSpot = parkingSpotUser.getSpot();
        entityRepository.add(parkingSpot);
        bookingRepository.add(parkingSpotUser);
        parkingSpot.getParkingSpots_Users().add(parkingSpotUser);
    }
}
