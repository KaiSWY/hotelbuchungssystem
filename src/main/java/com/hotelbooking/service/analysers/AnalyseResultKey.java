package com.hotelbooking.service.analysers;

import java.math.BigDecimal;

public enum AnalyseResultKey
{
    AVERAGE_PRICE(BigDecimal.class),
    AVERAGE_BOOKING_GUESTS_PER_DAY_THIS_MONTH(Double.class),
    AVERAGE_BOOKING_GUESTS_PER_DAY_LAST_MONTH(Double.class),
    AVERAGE_BOOKING_GUESTS_PER_DAY_THIS_YEAR(Double.class),
    AVERAGE_BOOKING_GUESTS_PER_DAY_LAST_YEAR(Double.class),
    AVERAGE_BOOKING_GUESTS_PER_DAY_SELECTED_TIME(Double.class),
    ACTIVITY_RANKING_VALUE(Double.class);



    private final Class<?> typ;

    AnalyseResultKey(Class<?> typ)
    {
        this.typ = typ;
    }

    public Class<?> getTyp()
    {
        return typ;
    }
}
