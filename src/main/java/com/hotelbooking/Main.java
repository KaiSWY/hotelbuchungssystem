package com.hotelbooking;

import com.hotelbooking.cli.HotelBookingCLI;

public class Main
{
    public static void main(String[] args)
    {
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