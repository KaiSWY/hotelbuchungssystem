package AnalyserTests;

import Mocks.MockRepository;
import com.hotelbooking.model.Booking;
import com.hotelbooking.model.User;
import com.hotelbooking.service.analysers.AnalyseResult;
import com.hotelbooking.service.analysers.BookingAnalyser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractBookingAnalyserTest<B extends Booking, T> {

    protected MockRepository<User> userRepository;
    protected MockRepository<T> entityRepository;
    protected MockRepository<B> bookingRepository;
    protected BookingAnalyser<B, T, Integer> bookingAnalyser;

    protected static final LocalDateTime START_TIME = LocalDateTime.now().minusDays(1);
    protected static final LocalDateTime END_TIME = LocalDateTime.now().minusHours(1);

    protected abstract void setupRepositories();
    protected abstract BookingAnalyser<B, T, Integer> setupBookingAnalyser();

    @BeforeEach
    void setUp() {
        setupRepositories();
        bookingAnalyser = setupBookingAnalyser();
    }

    @Test
    void testAnalyseAll() {
        // Setup data (common setup across all test classes)
        B entity = createEntity();
        addDataToRepositories(entity);

        // Perform analysis
        Map<T, AnalyseResult> results = bookingAnalyser.analyseAll();

        assertNotNull(results);
        assertEquals(1, results.size());

        AnalyseResult result = results.entrySet().iterator().next().getValue();
        assertNotNull(result);

        assertFalse(result.getResults().isEmpty());
    }

    protected abstract B createEntity();
    protected abstract void addDataToRepositories(B entity);
}
