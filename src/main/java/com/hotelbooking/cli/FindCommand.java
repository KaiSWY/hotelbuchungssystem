package com.hotelbooking.cli;

import java.util.ArrayList;
import java.util.List;

public class FindCommand
{
    private String[] args;
    private ExecuteCommand executeCommand;

    public FindCommand()
    {
    }

    public FindCommand(String[] args)
    {
        this.args = args;
        this.executeCommand = new ExecuteCommand();
    }

    //check all cases to get data to execute
    public void searchCommands()
    {
        //check if args match
        if (isValidInputStructure())
        {
            //start for loop to get value
            String mainCommand = args[0];
            List<String> actionArgsList = new ArrayList<>();
            for (int iArgValueCounter = 1; iArgValueCounter < (args.length - 1); iArgValueCounter += 3)
            {
                //add values to args list
                actionArgsList.add(args[iArgValueCounter]);
                actionArgsList.add(args[iArgValueCounter + 1]);
            }
            String[] actionArgs = actionArgsList.toArray(new String[0]);

            //call method in other class to run action
            this.executeCommand.runAction(mainCommand, actionArgs);
        }
    }

    //check if input structure is correct
    private boolean isValidInputStructure()
    {
        //check if length of args array has correct length -> check for error
        if ((this.args.length - 1) % 3 != 0)
        {
            return false;
        }

        //iterate args array
        for (int i = 1; i < (args.length - 1); i += 3)
        {
            //check if current group follows the pattern: -, "command", "value"
            if (!args[i].equals("-") || args[i + 1].equals("-") || args[i + 2].equals("-"))
            {
                return false;
            }
        }

        //all matches return true
        return true;
    }

    //method to get command from arg values
    public String getCommandValue(String[] argInputValues)
    {
        return argInputValues[2];
    }

    //method to get value from arg values
    public String getValue(String[] argInputValues)
    {
        return argInputValues[3];
    }
}
