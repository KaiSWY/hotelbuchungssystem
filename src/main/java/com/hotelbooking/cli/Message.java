package com.hotelbooking.cli;

import java.util.Arrays;

public class Message {
    private String[] args;

    public Message()
    {
    }

    public Message(String[] args) {
        this.args = args;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "Message{" +
                "args=" + Arrays.toString(args) +
                '}';
    }
}
