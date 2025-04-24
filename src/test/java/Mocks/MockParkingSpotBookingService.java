package Mocks;

import com.hotelbooking.model.Booking;
import com.hotelbooking.model.ParkingSpot_User;
import com.hotelbooking.service.BookingService;

import java.util.ArrayList;
import java.util.List;

public class MockParkingSpotBookingService extends BookingService<ParkingSpot_User>
{
    private final List<ParkingSpot_User> bookings = new ArrayList<>();

    public MockParkingSpotBookingService() {
        super(new MockRepository<>()); // Dummy Repository
    }

    @Override
    public void book(Booking booking) {
        if (booking instanceof ParkingSpot_User) {
            bookings.add((ParkingSpot_User) booking);
        }
    }

    @Override
    public void cancel(Integer bookingId) {
        bookings.removeIf(booking -> booking.getId() == bookingId);
    }

    @Override
    public List<ParkingSpot_User> getBookingsByEntityId(Integer entityId)
    {
        return bookings.stream().filter(booking -> booking.getSpot().getSpotNumber() == entityId).toList();
    }
}
