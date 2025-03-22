package com.hotelbooking.cli.enums;

public enum SubCommands
{

    ROOM_NUMBER("roomNumber");


    private String subCommands;

    SubCommands(String subCommands)
    {
        this.subCommands = subCommands;
    }

    @Override
    public String toString()
    {
        return "SubCommands{" +
                "subCommands='" + subCommands + '\'' +
                '}';
    }
}
