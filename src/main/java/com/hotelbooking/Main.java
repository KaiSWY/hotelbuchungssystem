package com.hotelbooking;

import com.hotelbooking.cli.CLI;
import com.hotelbooking.cli.Message;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Hotelbuchungssystem:");

        // -test
        String[] argValues = {"-", "roomNumber", "123", "-", "start", "01.01.2025"};

        //start cli
        Message message = new Message(argValues);
        CLI cli = new CLI(message);
        cli.execute();
    }
}