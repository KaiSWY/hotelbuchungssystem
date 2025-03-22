package com.hotelbooking.cli.enums;

public enum MainCommands {
    CREATE_USER("--createUser"),
    CREATE_BOOKING("--createBooking"),
    BOOK_PARKING_SPOT("--bookParkingSpot");

    private String command;

    MainCommands(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return "MainCommands{" +
                "command='" + command + '\'' +
                '}';
    }
}
