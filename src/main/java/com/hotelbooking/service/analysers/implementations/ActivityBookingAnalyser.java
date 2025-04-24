package com.hotelbooking.service.analysers.implementations;

import com.hotelbooking.model.Activity;
import com.hotelbooking.model.Activity_User;
import com.hotelbooking.model.Booking;
import com.hotelbooking.repository.IRepository;
import com.hotelbooking.service.analysers.AnalyseResult;
import com.hotelbooking.service.analysers.AnalyseResultKey;
import com.hotelbooking.service.analysers.BookingAnalyser;
import com.hotelbooking.service.analysers.implementations.RankingStrategy.BookingTimesRanking;
import com.hotelbooking.service.analysers.implementations.RankingStrategy.IActivityRankingStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ActivityBookingAnalyser extends BookingAnalyser<Activity_User, Activity, Integer>
{
    public ActivityBookingAnalyser(IRepository<Activity, Integer> repository, IRepository<Activity_User, Integer> bookingRepository)
    {
        super(repository, bookingRepository);
    }

    @Override
    protected List<Booking> getFilteredBookings(List<Activity_User> bookings, Activity entity, LocalDateTime startTime, LocalDateTime endTime)
    {
        bookings = bookings.stream()
                .filter(booking -> booking.getActivity().getActivityId() == entity.getActivityId())
                .filter(booking -> !booking.getEndDateTime().isBefore(startTime) && !booking.getStartDateTime().isAfter(endTime))
                .toList();

        return convertExtendsBookingListToBookingList(bookings);
    }

    @Override
    protected Map<AnalyseResultKey, Object> getResultKeys(List<Activity_User> bookings,
                                                          Activity entity,
                                                          LocalDateTime startTime,
                                                          LocalDateTime endTime)
    {
        IActivityRankingStrategy activityRankingStrategy = new BookingTimesRanking();

        Map<AnalyseResultKey, Object> resultKeys = super.getResultKeys(bookings, entity, startTime, endTime);
        resultKeys.put(AnalyseResultKey.ACTIVITY_RANKING_VALUE, activityRankingStrategy.getActivityRankingValue(entity));
        return resultKeys;
    }

    @Override
    public Map<Activity, AnalyseResult> analyseAllBetweenDate(LocalDateTime startTime, LocalDateTime endTime)
    {
        Map<Activity, AnalyseResult> result = super.analyseAllBetweenDate(startTime, endTime);
        return sortByValue(result);
    }

    private Map<Activity, AnalyseResult> sortByValue(Map<Activity, AnalyseResult> map)
    {
        List<Map.Entry<Activity, AnalyseResult>> list = new ArrayList<>(map.entrySet());
        list.sort((entry1, entry2) ->
        {
            double value1 = (double) entry1.getValue().getResults().get(AnalyseResultKey.ACTIVITY_RANKING_VALUE);
            double value2 = (double) entry2.getValue().getResults().get(AnalyseResultKey.ACTIVITY_RANKING_VALUE);
            return Double.compare(value1, value2);
        });

        Map<Activity, AnalyseResult> result = new LinkedHashMap<>();
        for (Map.Entry<Activity, AnalyseResult> entry : list)
        {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}

