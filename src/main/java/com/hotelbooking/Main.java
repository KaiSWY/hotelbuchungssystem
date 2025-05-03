package com.hotelbooking;

import com.hotelbooking.cli.HotelBookingCLI;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println(">--Hotelbuchungssystem--<");

        //start cli
        HotelBookingCLI hotelBookingCLI = new HotelBookingCLI(args);
        hotelBookingCLI.execute();
    }
}