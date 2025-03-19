package com.hotelbooking.service.analysers;

import com.hotelbooking.model.Booking;
import com.hotelbooking.repository.IRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BookingAnalyser<T, ID> extends Analyser<T, ID> implements IBookingAnalyser<T>
{
    protected final IRepository<Booking, Integer> bookingRepository;

    protected BookingAnalyser(IRepository<T, ID> repository, IRepository<Booking, Integer> bookingRepository)
    {
        super(repository);
        this.bookingRepository = bookingRepository;
    }

    @Override
    public AnalyseResult analyse(T entity)
    {
        return analyseBetweenDate(entity, LocalDateTime.MIN, LocalDateTime.now());
    }

    @Override
    public AnalyseResult analyseFromDate(T entity, LocalDateTime startTime)
    {
        LocalDateTime endTime = LocalDateTime.now();
        return analyseBetweenDate(entity, startTime, endTime);
    }

    @Override
    public Map<T, AnalyseResult> analyseAllFromDate(LocalDateTime startTime)
    {
        LocalDateTime endTime = LocalDateTime.now();
        return analyseAllBetweenDate(startTime, endTime);
    }

    @Override
    public Map<T, AnalyseResult> analyseAllBetweenDate(LocalDateTime startTime, LocalDateTime endTime)
    {
        List<T> allEntities = repository.getAll();
        return analyseResultsFromEntitiesBetweenDate(allEntities, startTime, endTime);
    }

    private Map<T, AnalyseResult> analyseResultsFromEntitiesBetweenDate(List<T> entities, LocalDateTime startTime, LocalDateTime endTime)
    {
        Map<T, AnalyseResult> results = new HashMap<>();
        for (T entity : entities)
        {
            results.put(entity, analyseBetweenDate(entity, startTime, endTime));
        }
        return results;
    }
}
