package com.hotelbooking.service.analysers.implementations.RankingStrategy;

import com.hotelbooking.model.Activity;

public interface IActivityRankingStrategy
{
    double getActivityRankingValue(Activity activity);
}
