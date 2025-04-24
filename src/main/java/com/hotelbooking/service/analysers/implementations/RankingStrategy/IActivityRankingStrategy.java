package com.hotelbooking.service.analysers.implementations.RankingStrategy;

import com.hotelbooking.model.Activity;

import java.util.List;

public interface IActivityRankingStrategy
{
    double getActivityRankingValue(Activity activity);
}
