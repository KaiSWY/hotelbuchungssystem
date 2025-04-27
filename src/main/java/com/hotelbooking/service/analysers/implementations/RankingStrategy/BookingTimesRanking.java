package com.hotelbooking.service.analysers.implementations.RankingStrategy;

import com.hotelbooking.model.Activity;

import java.time.LocalDateTime;

public class BookingTimesRanking implements IActivityRankingStrategy
{
    @Override
    public double getActivityRankingValue(Activity activity)
    {
        return countBookingsLastYear(activity) + countBookingsLastMonth(activity) * 2;
    }

    private long countBookingsLastYear(Activity activity)
    {
        return countBookingsSince(activity, LocalDateTime.now().minusYears(1));
    }

    private long countBookingsLastMonth(Activity activity)
    {
        return countBookingsSince(activity, LocalDateTime.now().minusMonths(1));
    }

    private long countBookingsSince(Activity activity, LocalDateTime since)
    {
        return activity.getActivity_users().stream()
                .filter(activityUser -> activityUser.getStartDateTime().isAfter(since))
                .count();
    }
}
