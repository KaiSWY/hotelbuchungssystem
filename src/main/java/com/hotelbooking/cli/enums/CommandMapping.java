package com.hotelbooking.cli.enums;

import java.util.*;

public abstract class CommandMapping
{
    private static final Map<MainCommands, List<SubCommands>> commandsMap = new HashMap<>();

    static
    {
        //define sub commands for create user main command
        commandsMap.put(MainCommands.CREATE_USER, Arrays.asList(
                SubCommands.FIRST_NAME,
                SubCommands.LAST_NAME,
                SubCommands.MAIL,
                SubCommands.COUNTRY,
                SubCommands.ZIP_CODE,
                SubCommands.CITY,
                SubCommands.STREET
        ));


    }

    //method to get sub commands for specific main command
    public static List<SubCommands> getSubCommands(MainCommands command)
    {
        return commandsMap.getOrDefault(command, Collections.emptyList());
    }

    //method to check if sub commands match to main command
    public static boolean subCommandsPartOfMainCommands(MainCommands mainCommand, String[] subCommands)
    {
        // Get the allowed subcommands for the given main command
        List<SubCommands> allowedSubCommands = getSubCommands(mainCommand);

        // Convert subCommands array (String) to a list of SubCommands enum values
        List<SubCommands> subCommandEnums = Arrays.stream(subCommands)
                .map(SubCommands::findCommandsByValue)  // Convert string to enum
                .filter(Objects::nonNull)  // Filter out nulls (invalid subcommands)
                .toList();

        // Check if all provided subcommands are part of the allowed subcommands
        return allowedSubCommands.containsAll(subCommandEnums);
    }
}
