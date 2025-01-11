package com.example.app;

import java.util.ArrayList;

enum Coefficient {
    INTERCEPT(15.577426724451641),
    DISTANCE(0.01785479684723082),
    STOPS(30.79842499890535),
    VOLUME(0.01056251732193303);

    private final double value;

    Coefficient(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}

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

    public int getStops() {
        return flight.size() - 1;
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

    public double getPrice() {
        double distance = 0;
        double airlineVolume = 0;
        for (Flight f : flight) {
            // Get distance between airports
            distance += Flight.getDistance(f.getDepartureAirport(), f.getArrivalAirport());
            airlineVolume += f.getAirline().getBookings();
        }

        // Get volume
        airlineVolume /= flight.size();

        // Calculate price: Price = INTERCEPT + DISTANCE * distance + STOPS * stops +
        // DEMAND * demand
        double price = Coefficient.INTERCEPT.getValue() + Coefficient.DISTANCE.getValue() * distance
                + Coefficient.STOPS.getValue() * getStops() + Coefficient.VOLUME.getValue() * airlineVolume;

        return price;
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
