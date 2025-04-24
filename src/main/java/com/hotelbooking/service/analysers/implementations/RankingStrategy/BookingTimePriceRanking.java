package com.hotelbooking.service.analysers.implementations.RankingStrategy;

import com.hotelbooking.model.Activity;

import java.time.LocalDateTime;

public class BookingTimePriceRanking implements IActivityRankingStrategy
{
    @Override
    public double getActivityRankingValue(Activity activity)
    {
        return activity.getPricePerPerson() *
                activity.getActivity_users().stream().filter(activityUser ->
                        activityUser.getStartDateTime().isAfter(LocalDateTime.now().minusYears(1))).count();
    }
}
