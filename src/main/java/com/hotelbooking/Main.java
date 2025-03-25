package com.hotelbooking;

import com.hotelbooking.cli.HotelBookingCLI;

import java.util.logging.Level;

public class Main
{
    public static void main(String[] args)
    {
        //java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        System.out.println(">--Hotelbuchungssystem--<");

        //test
        String[] argValues = {"createUser",
                "--firstName", "kai",
                "--lastName", "Schablowsky",
                "--mail", "kai.schablowsky@t-online.de",
                "--country", "Germany",
                "--zipCode", "76148",
                "--city", "Karlsruhe",
                "--street", "Hauptstrasse",
                "--houseNumber", "12"};

        String[] deleteUserArgValues = {"deleteUser",
                "--id", "2"
        };

        String[] bookRoomArgValues = {"bookRoom",
            "--id", "1",
            "--roomNumber", "",
            "--startDate", "2020-01-01",
            "--endDate", "2020-01-02",};

        //start cli
        HotelBookingCLI hotelBookingCLI = new HotelBookingCLI(deleteUserArgValues);
        hotelBookingCLI.execute();
    }
}