package com.hotelbooking.cli.enums;

import java.util.Arrays;
import java.util.Objects;

public enum SubCommands
{
    ID("--id"),
    FIRST_NAME("--firstName"),
    LAST_NAME("--lastName"),
    MAIL("--mail"),
    COUNTRY("--country"),
    ZIP_CODE("--postalCode"),
    CITY("--city"),
    STREET("--street"),
    HOUSE_NUMBER("--houseNumber"),
    ROOM_NUMBER("--roomNumber"),
    START_DATE("--startDate"),
    END_DATE("--endDate"),
    ACTIVITY_ID("--activityId");

    private String subCommands;

    SubCommands(String subCommands)
    {
        this.subCommands = subCommands;
    }

    public String getSubCommands()
    {
        return subCommands;
    }

    //method to check if command is part of enums
    public static boolean commandsPartOfEnum(String[] subCommands)
    {
        for (String command : subCommands)
        {
            for (SubCommands currentSubCommand : SubCommands.values())
            {
                if (Objects.equals(currentSubCommand.getSubCommands(), command))
                {
                    return true;
                }
            }
        }

        return false;
    }

    //method to find commands by value
    public static SubCommands findCommandsByValue(String commandValue)
    {
        for (SubCommands currentSubCommand : SubCommands.values())
        {
            if (Objects.equals(currentSubCommand.getSubCommands(), commandValue))
            {
                return currentSubCommand;
            }
        }
        return null;
    }

    @Override
    public String toString()
    {
        return "SubCommands{" +
                "subCommands='" + subCommands + '\'' +
                '}';
    }
}
