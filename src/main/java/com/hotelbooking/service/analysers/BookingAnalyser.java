package com.hotelbooking.service.analysers;

import com.hotelbooking.model.Booking;
import com.hotelbooking.repository.IRepository;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BookingAnalyser<B extends Booking, T, ID> extends Analyser<T, ID> implements IBookingAnalyser<T>
{
    protected final IRepository<B, Integer> bookingRepository;

    protected BookingAnalyser(IRepository<T, ID> repository, IRepository<B, Integer> bookingRepository)
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
        List<B> bookings = bookingRepository.getAll();
        return calculateAnalyseResult(bookings, entity, startTime, endTime);
    }

    protected abstract List<Booking> getFilteredBookings(List<B> bookings, T entity, LocalDateTime startTime, LocalDateTime endTime);

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

    protected double calculateAverageGuestsPerDays(List<B> bookings, T entity, LocalDateTime startTime, LocalDateTime endTime)
    {

        List<Booking> filteredBookings = getFilteredBookings(bookings, entity, startTime, endTime);
        if (filteredBookings.isEmpty()) {
            return 0;
        }

        int numberGuests = filteredBookings.size();
        long totalDurationInMinutes = ChronoUnit.MINUTES.between(startTime, endTime);
        double totalDurationInDays = totalDurationInMinutes / (24.0 * 60);
        return numberGuests / totalDurationInDays;
    }

    protected Map<AnalyseResultKey, Object> getResultKeys(List<B> bookings,
                                                          T entity,
                                                          LocalDateTime startTime,
                                                          LocalDateTime endTime)
    {
        Map<AnalyseResultKey, Object> resultKeys = new HashMap<>();

        LocalDateTime lastMonthBeginning = LocalDateTime.now().minusMonths(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime lastMonthEnding = YearMonth.now().minusMonths(1).atEndOfMonth().atTime(23, 59, 59, 999_999_999);
        resultKeys.put(AnalyseResultKey.AVERAGE_BOOKING_GUESTS_PER_DAY_LAST_MONTH, calculateAverageGuestsPerDays(bookings, entity, lastMonthBeginning, lastMonthEnding));

        LocalDateTime thisMonthBeginning = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        resultKeys.put(AnalyseResultKey.AVERAGE_BOOKING_GUESTS_PER_DAY_THIS_MONTH, calculateAverageGuestsPerDays(bookings, entity, thisMonthBeginning, LocalDateTime.now()));

        LocalDateTime lastYearBeginning = LocalDateTime.now().minusYears(1).withDayOfYear(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime lastYearEnding = LocalDateTime.of(LocalDateTime.now().getYear()-1, 12, 31, 23, 59, 59, 999_999_999);
        resultKeys.put(AnalyseResultKey.AVERAGE_BOOKING_GUESTS_PER_DAY_LAST_YEAR, calculateAverageGuestsPerDays(bookings, entity, lastYearBeginning, lastYearEnding));

        LocalDateTime thisYearBeginning = LocalDateTime.now().withDayOfYear(1).withHour(0).withMinute(0).withSecond(0);
        resultKeys.put(AnalyseResultKey.AVERAGE_BOOKING_GUESTS_PER_DAY_THIS_YEAR, calculateAverageGuestsPerDays(bookings, entity, thisYearBeginning, LocalDateTime.now()));

        if (!startTime.isEqual(LocalDateTime.MIN))
        {
            resultKeys.put(AnalyseResultKey.AVERAGE_BOOKING_GUESTS_PER_DAY_SELECTED_TIME, calculateAverageGuestsPerDays(bookings, entity, startTime, endTime));
        }

        return resultKeys;
    }

    private AnalyseResult calculateAnalyseResult(List<B> bookings, T entity, LocalDateTime startTime, LocalDateTime endTime)
    {
        AnalyseResult result = new AnalyseResult();
        Map<AnalyseResultKey, Object> resultKeys = getResultKeys(bookings, entity, startTime, endTime);
        for (Map.Entry<AnalyseResultKey, Object> entry : resultKeys.entrySet())
        {
            result.addResult(entry.getKey(), entry.getValue());
        }
        return result;
    }

    protected List<Booking> convertExtendsBookingListToBookingList(List<B> extendsBookingList)
    {
        List<Booking> bookings = new ArrayList<>();
        for (B extendsBooking : extendsBookingList)
        {
            bookings.add(extendsBooking);
        }
        return bookings;
    }
}
