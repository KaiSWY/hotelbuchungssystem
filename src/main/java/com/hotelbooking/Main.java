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
                "--houseNumber", "12"
        };

        String[] deleteUserArgValues = {"deleteUser",
                "--id", "1"
        };

        String[] bookRoomArgValues = {"createRoomBooking",
                "--roomNumber", "1",
                "--mail", "kai.schablowsky@t-online.de",
                "--startDate", "01.01.2025",
                "--endDate", "02.01.2025"
        };

        String[] bookParkingSpotValues = {"bookParkingSpot",
                "--id", "1",
                "--mail", "kai.schablowsky@t-online.de",
                "--startDate", "01.01.2025",
                "--endDate", "02.01.2025"
        };

        String[] bookRestaurantTable = {"bookRestaurantTable",
                "--id", "1",
                "--mail", "kai.schablowsky@t-online.de",
                "--startDate", "01.01.2025",
                "--endDate", "02.01.2025"
        };

        String[] deleteBookedRestaurantTable = {"deleteBookedParkingSpot",
                "--id", "1"
        };

        String[] getBookedRestaurantTableArgs = {"getBookedRestaurantTable",
                "--id", "1"
        };

        //start cli
        HotelBookingCLI hotelBookingCLI = new HotelBookingCLI(getBookedRestaurantTableArgs);
        hotelBookingCLI.execute();
    }
}