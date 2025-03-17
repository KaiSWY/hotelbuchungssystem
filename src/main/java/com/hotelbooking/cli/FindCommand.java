package com.hotelbooking.cli;

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
    public void searchCommands() {
        //check if args match
        if (isValidInputStructure())
        {
            //start for loop to get value
            for (int iArgValueCounter = 0; iArgValueCounter < args.length; iArgValueCounter += 3)
            {
                String[] argTriplet = {args[iArgValueCounter], args[iArgValueCounter + 1], args[iArgValueCounter + 2]};

                //return values - test
                System.out.println("command:" + getCommandValue(argTriplet));
                System.out.println("value:" + getValue(argTriplet));

                //call method in other class to run action
                boolean result = this.executeCommand.runAction(getCommandValue(argTriplet), getValue(argTriplet));
                System.out.println(result ? "true" : "false");
            }
        }
    }

    //check if input structure is correct
    private boolean isValidInputStructure()
    {
        //check if length of args array has correct length -> check for error
        if (this.args.length % 3 != 0)
        {
            return false;
        }

        //iterate args array
        for (int i = 0; i < args.length; i += 3)
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
    private String getCommandValue(String[] argInputValues)
    {
        return argInputValues[1];
    }

    //method to get value from arg values
    private String getValue(String[] argInputValues)
    {
        return argInputValues[2];
    }
}
