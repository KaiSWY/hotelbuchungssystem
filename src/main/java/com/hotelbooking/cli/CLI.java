package com.hotelbooking.cli;

public class CLI {
    private Message message;
    private FindCommand findCommand;

    public CLI()
    {
    }

    public CLI(Message message) {
        this.message = message;
        this.findCommand = new FindCommand(message.getArgs());
    }

    public void execute() {
        //check if values can be found
        this.findCommand.searchCommands();
    }
}
