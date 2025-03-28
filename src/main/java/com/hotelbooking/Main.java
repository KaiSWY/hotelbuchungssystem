package com.hotelbooking;

import com.hotelbooking.cli.HotelBookingCLI;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println(">--Hotelbuchungssystem--<");

        //test
        String[] deleteUserArgValues = {"deleteUser",
                "--id", "1"
        };

        //start cli
        HotelBookingCLI hotelBookingCLI = new HotelBookingCLI(deleteUserArgValues);
        hotelBookingCLI.execute();
    }
}