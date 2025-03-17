package com.hotelbooking.cli;

public class ExecuteCommand {
    private String command;
    private String value;

    //controller classes


    public ExecuteCommand()
    {
    }

    public ExecuteCommand(String command, String value)
    {
        this.command = command;
        this.value = value;
    }

    public String getCommand()
    {
        return command;
    }

    public void setCommand(String command)
    {
        this.command = command;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    //method to run action
    public boolean runAction(String command, String value)
    {
        //try catch for error handling
        try
        {
            switch (command)
            {
                case "roomNumber":

                    break;
                case "start":

                    break;
                case "end":

                    break;
                default:
                    return false;
            }

            return true;
        }
        catch (Exception eRunAction)
        {
            return false;
        }
    }
}
