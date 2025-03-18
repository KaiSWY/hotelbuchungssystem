package com.hotelbooking.service.analysers;

import java.math.BigDecimal;

public enum AnalyseResultKey
{
    AVERAGE_PRICE(BigDecimal.class),
    AVERAGE_BOOKING_GUESTS_THIS_MONTH(Double.class),
    AVERAGE_BOOKING_GUESTS_LAST_MONTH(Double.class),
    AVERAGE_BOOKING_GUESTS_THIS_YEAR(Double.class),
    AVERAGE_BOOKING_GUESTS_LAST_YEAR(Double.class),
    AVERAGE_BOOKING_GUESTS_SELECTED_TIME(Double.class);


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
