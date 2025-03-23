package com.hotelbooking;

import com.hotelbooking.cli.HotelBookingCLI;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println(">--Hotelbuchungssystem--<");

        // -test
        String[] argValues = {"createUser", "--firstName", "kai", "--end", "Schablowsky"};

        //start cli
        HotelBookingCLI hotelBookingCLI = new HotelBookingCLI(argValues);
        hotelBookingCLI.execute();
    }
}