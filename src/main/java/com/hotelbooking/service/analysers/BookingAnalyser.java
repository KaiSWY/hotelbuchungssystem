package com.hotelbooking.service.analysers;

import com.hotelbooking.model.Booking;
import com.hotelbooking.repository.IRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
    public AnalyseResult analyseBetweenDate(T entity, LocalDateTime startTime, LocalDateTime endTime)
    {
        List<Booking> bookings = bookingRepository.getAll();
        List<Booking> filteredBookings = getFilteredBookings(bookings, entity, startTime, endTime);
        return calculateAnalyseResult(filteredBookings, startTime, endTime);
    }

    protected abstract List<Booking> getFilteredBookings(List<Booking> bookings, T entity, LocalDateTime startTime, LocalDateTime endTime);

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

    protected double calculateAverageGuestsPerDays(List<Booking> bookings, LocalDateTime startTime, LocalDateTime endTime)
    {
        int numberGuests = bookings.size();
        double duration = ChronoUnit.MINUTES.between(startTime, endTime);
        if (numberGuests == 0)
        {
            return 0;
        }
        return (duration / numberGuests) * 60 * 24;
    }

    protected Map<AnalyseResultKey, Object> getResultKeys(List<Booking> bookings,
                                                          LocalDateTime startTime,
                                                          LocalDateTime endTime)
    {
        Map<AnalyseResultKey, Object> resultKeys = new HashMap<>();
        resultKeys.put(AnalyseResultKey.AVERAGE_BOOKING_GUESTS_SELECTED_TIME, calculateAverageGuestsPerDays(bookings, startTime, endTime));
        return resultKeys;
    }

    private AnalyseResult calculateAnalyseResult(List<Booking> bookings, LocalDateTime startTime, LocalDateTime endTime)
    {
        AnalyseResult result = new AnalyseResult();
        Map<AnalyseResultKey, Object> resultKeys = getResultKeys(bookings, startTime, endTime);
        for (Map.Entry<AnalyseResultKey, Object> entry : resultKeys.entrySet())
        {
            result.addResult(entry.getKey(), entry.getValue());
        }
        return result;
    }

    protected <B extends Booking> List<B> convertBookingListToExtendsBookingList(List<Booking> bookings, Class<B> type)
    {
        List<B> extendsBookingList = new ArrayList<>();

        for (Booking booking : bookings)
        {
            if (type.isInstance(booking))
            {
                extendsBookingList.add(type.cast(booking));
            }
            else
            {
                throw new IllegalArgumentException("Invalid booking object. " + type.getSimpleName() + " object expected");
            }
        }
        return extendsBookingList;
    }

    protected <B extends Booking> List<Booking> convertExtendsBookingListToBookingList(List<B> extendsBookingList)
    {
        List<Booking> bookings = new ArrayList<>();
        for (B extendsBooking : extendsBookingList)
        {
            bookings.add(extendsBooking);
        }
        return bookings;
    }
}
