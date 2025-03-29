package com.hotelbooking;

import com.hotelbooking.cli.HotelBookingCLI;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println(">--Hotelbuchungssystem--<");

        //test
        String[] args2 = {"getBookedActivity",
                "--id", "1"
        };

        //start cli
        HotelBookingCLI hotelBookingCLI = new HotelBookingCLI(args2);
        hotelBookingCLI.execute();
    }
}