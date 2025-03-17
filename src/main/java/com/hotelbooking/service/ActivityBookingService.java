package com.hotelbooking.service;

import com.hotelbooking.model.Activity;
import com.hotelbooking.model.Activity_User;
import com.hotelbooking.model.Booking;
import com.hotelbooking.model.User;
import com.hotelbooking.repository.IRepository;

import java.time.LocalDateTime;
import java.util.List;

public class ActivityBookingService extends BookingService<Activity_User>
{
    private final IRepository<User, Integer> userRepository;
    private final IRepository<Activity, Integer> activityRepository;

    protected ActivityBookingService(IRepository<Activity_User, Integer> repository,
                                     IRepository<User, Integer> userRepository,
                                     IRepository<Activity, Integer> activityRepository)
    {
        super(repository);
        this.userRepository = userRepository;
        this.activityRepository = activityRepository;
    }

    @Override
    public void book(Booking booking)
    {
        Activity_User activityUserBooking;
        if (booking instanceof Activity_User)
        {
            activityUserBooking = (Activity_User) booking;
        }
        else
        {
            throw new IllegalArgumentException("Invalid booking object");
        }

        User user = activityUserBooking.getUser();
        Activity activity = activityUserBooking.getActivity();
        LocalDateTime startDateTime = booking.getStartDateTime();
        LocalDateTime endDateTime = booking.getEndDateTime();

        if (!toManyMembers(activity, startDateTime, endDateTime))
        {
            Activity_User activityUser = new Activity_User(activity, user, startDateTime, endDateTime);
            persistActivityUser(activityUser);
        }
        else
        {
            throw new IllegalArgumentException("Activity is not available for the selected time period");
        }
    }

    @Override
    public void cancel(Integer bookingId)
    {
        Activity_User activityUser = repository.getById(bookingId);
        if (activityUser == null)
        {
            throw new IllegalArgumentException("Cancellation process failed. Activity booking not found with ID: " + bookingId);
        }
        persistActivityUser(activityUser);
    }

    @Override
    public List<Activity_User> getBookingsByEntityId(Integer entityId)
    {
        return activityRepository.getById(entityId).getActivity_users();
    }

    private boolean toManyMembers(Activity activity, LocalDateTime start, LocalDateTime end)
    {
        int memberCounter = 0;
        int maxMembers = activity.getMaxParticipants();
        List<Activity_User> bookings = getBookingsByEntityId(activity.getActivityId());
        for (Activity_User booking : bookings)
        {
            if (timeInTimeSpan(start, booking.getStartDateTime(), booking.getEndDateTime())
                    || timeInTimeSpan(end, booking.getStartDateTime(), booking.getEndDateTime()))
            {
                memberCounter++;
            }
        }
        return memberCounter > maxMembers;
    }

    private void persistActivityUser(Activity_User activityUser)
    {
        User user = activityUser.getUser();
        Activity activity = activityUser.getActivity();

        user.getActivity_users().add(activityUser);
        userRepository.update(user);
        activity.getActivity_users().add(activityUser);
        activityRepository.update(activity);
        repository.add(activityUser);
    }

    private void persistDeleteActivityUser(Activity_User activityUser)
    {
        User user = activityUser.getUser();
        Activity activity = activityUser.getActivity();

        user.getActivity_users().remove(activityUser);
        userRepository.update(user);
        activity.getActivity_users().remove(activityUser);
        activityRepository.update(activity);
        super.cancel(activityUser.getId());
    }
}
