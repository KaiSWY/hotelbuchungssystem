package com.hotelbooking;

import com.hotelbooking.cli.HotelBookingCLI;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println(">--Hotelbuchungssystem--<");

        // -test
        String[] argValues = {"--createUser", "-roomNumber", "123", "-start", "01.01.2025"};

        //start cli
        HotelBookingCLI hotelBookingCLI = new HotelBookingCLI(argValues);
        hotelBookingCLI.execute();
    }
}