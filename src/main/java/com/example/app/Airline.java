package com.example.app;

public class Airline {
    private final String code;
    private final String name;
    private int bookings;

    Airline(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getBookings() {
        return bookings;
    }

    public void newBooking() {
        this.bookings++;
    }

    @Override
    public String toString() {
        return "Code: " + code + " \nName: " + name + " \nBookings: " + bookings;
    }
}
