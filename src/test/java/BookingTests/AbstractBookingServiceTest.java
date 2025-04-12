package BookingTests;
import Mocks.MockRepository;
import com.hotelbooking.model.Booking;
import com.hotelbooking.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractBookingServiceTest<T extends Booking> {

    protected BookingService<T> bookingService;
    protected MockRepository<T> bookingRepository;

    protected abstract BookingService<T> createBookingService();
    protected abstract T createValidBooking();
    protected abstract T createConflictingBooking();

    @BeforeEach
    void setUp() {
        bookingRepository = new MockRepository<>();
        bookingService = createBookingService();
    }

    @Test
    void testSuccessfulBooking() {
        T booking = createValidBooking();

        bookingService.book(booking);

        List<T> allBookings = bookingRepository.getAll();
        assertEquals(1, allBookings.size());
        assertEquals(0, allBookings.getFirst().getId());
    }

    @Test
    void testBookingConflict() {
        T conflict = createConflictingBooking();
        assertThrows(IllegalArgumentException.class, () -> bookingService.book(conflict));
    }

    @Test
    void testCancel() {
        T booking = createValidBooking();
        bookingRepository.add(booking);
        int id = bookingRepository.getIdFor(booking); // Hilfsmethode
        booking.setId(id);

        bookingService.cancel(id);

        assertNull(bookingRepository.getById(id));
    }

    @Test
    void testCancelNonExistentBooking() {
        assertThrows(IllegalArgumentException.class, () -> bookingService.cancel(999));
    }
}

