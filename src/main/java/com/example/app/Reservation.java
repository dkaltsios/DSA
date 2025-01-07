package com.example.app;

import java.util.ArrayList;

public final class Reservation {
    private final ArrayList<Flight> flight;
    private final ArrayList<Seat> seat;
    private final String username;
    private final String code;

    public Reservation(ArrayList<Flight> flight, String username) {
        this.username = username;
        this.flight = flight;
        this.seat = new ArrayList<>();
        this.code = createCode();
        for (Flight f : flight) {
            setSeat(f);
        }
    }

    public ArrayList<Flight> getFlight() {
        return flight;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Seat> getSeat() {
        return seat;
    }

    public void setSeat(Flight flight) {
        for (Seat s : flight.getPlane().getSeats()) {
            if (!s.isBooked()) {
                flight.getPlane().setBooked(s.getSeatCode());
                this.seat.add(s);
                return;
            }
        }
    }

    public String getCode() {
        return code;
    }

    public String createCode() {
        String code = username;
        for (Flight f : flight) {
            code += f.getCode();
        }
        return code;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "flight=" + flight +
                ", username='" + username + '\'' +
                ", seat=" + seat +
                '}';
    }
}
