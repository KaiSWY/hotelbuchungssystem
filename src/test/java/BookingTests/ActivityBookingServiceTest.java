package BookingTests;

import Mocks.MockRepository;
import com.hotelbooking.model.Activity;
import com.hotelbooking.model.Activity_User;
import com.hotelbooking.model.User;
import com.hotelbooking.service.ActivityBookingService;
import com.hotelbooking.service.BookingService;

import java.time.LocalDateTime;

public class ActivityBookingServiceTest extends AbstractBookingServiceTest<Activity_User> {

    private MockRepository<User> userRepository;
    private MockRepository<Activity> activityRepository;

    @Override
    protected BookingService<Activity_User> createBookingService() {
        userRepository = new MockRepository<>();
        activityRepository = new MockRepository<>();

        User user = new User();
        Activity activity = new Activity();
        activity.setActivityId(0);

        userRepository.add(user);
        activityRepository.add(activity);

        return new ActivityBookingService(bookingRepository, userRepository, activityRepository);
    }

    @Override
    protected Activity_User createValidBooking() {
        User user = userRepository.getAll().getFirst();
        Activity activity = activityRepository.getAll().getFirst();
        Activity_User activityUser = new Activity_User(activity, user, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2));
        user.getActivity_users().add(activityUser);
        return activityUser;
    }

    @Override
    protected Activity_User createConflictingBooking() {
        Activity_User existing = createValidBooking();
        existing.getActivity().getActivity_users().add(existing);
        return new Activity_User(
                existing.getActivity(),
                existing.getUser(),
                existing.getStartDateTime().plusHours(1),
                existing.getEndDateTime().plusHours(1)
        );
    }
}
