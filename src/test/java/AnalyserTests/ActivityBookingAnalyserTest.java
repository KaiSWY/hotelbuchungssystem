package AnalyserTests;

import Mocks.MockRepository;
import com.hotelbooking.model.Activity;
import com.hotelbooking.model.Activity_User;
import com.hotelbooking.model.User;
import com.hotelbooking.service.analysers.BookingAnalyser;
import com.hotelbooking.service.analysers.implementations.ActivityBookingAnalyser;

import java.time.LocalDateTime;

public class ActivityBookingAnalyserTest extends AbstractBookingAnalyserTest<Activity_User, Activity> {

    @Override
    protected void setupRepositories() {
        userRepository = new MockRepository<>();
        entityRepository = new MockRepository<>();
        bookingRepository = new MockRepository<>();
    }

    @Override
    protected BookingAnalyser<Activity_User, Activity, Integer> setupBookingAnalyser() {

        return new ActivityBookingAnalyser(entityRepository, bookingRepository);
    }

    @Override
    protected Activity_User createEntity() {
        Activity activity = new Activity();
        activity.setActivityId(0);
        activity.setName("Yoga");

        User user = new User();
        user.setUserId(0);
        user.setEmail("yoga@yoga.com");

        Activity_User activityUser = new Activity_User();
        activityUser.setActivity(activity);
        activityUser.setUser(user);
        activityUser.setStartDateTime(START_TIME);
        activityUser.setEndDateTime(END_TIME);

        return activityUser;
    }

    @Override
    protected void addDataToRepositories(Activity_User activityUser) {
        User user = activityUser.getUser();
        userRepository.add(user);
        user.getActivity_users().add(activityUser);

        Activity activity = activityUser.getActivity();
        entityRepository.add(activity);
        bookingRepository.add(activityUser);
        activity.getActivity_users().add(activityUser);

        bookingRepository.add(activityUser);
    }
}