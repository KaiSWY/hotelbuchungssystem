package com.hotelbooking.service.analysers.implementations.RankingStrategy;

import com.hotelbooking.model.Activity;

import java.time.LocalDateTime;

public class BookingTimesRanking implements IActivityRankingStrategy
{
    @Override
    public double getActivityRankingValue(Activity activity)
    {
        long bookingsLastYear = activity.getActivity_users().stream().filter(activityUser ->
                activityUser.getStartDateTime().isAfter(LocalDateTime.now().minusYears(1))).count();
        long bookingsLastMonth = activity.getActivity_users().stream().filter(activityUser ->
                activityUser.getStartDateTime().isAfter(LocalDateTime.now().minusMonths(1))).count();
        return bookingsLastYear + bookingsLastMonth * 2;
    }
}
