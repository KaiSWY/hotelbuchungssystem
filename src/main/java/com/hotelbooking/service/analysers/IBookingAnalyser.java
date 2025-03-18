package com.hotelbooking.service.analysers;

import java.time.LocalDateTime;
import java.util.Map;

public interface IBookingAnalyser<T> extends IAnalyser<T>
{
    AnalyseResult analyseFromDate(T entity, LocalDateTime startTime);

    AnalyseResult analyseBetweenDate(T entity, LocalDateTime startTime, LocalDateTime endTime);

    Map<T, AnalyseResult> analyseAllFromDate(LocalDateTime startTime);

    Map<T, AnalyseResult> analyseAllBetweenDate(LocalDateTime startTime, LocalDateTime endTime);
}
