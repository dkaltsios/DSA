package com.example.app;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Flight {
    private final String code;
    // TODO: WE NEED TO ADD BASE SALES PRICE FOR FLIGHT
    private final int number;
    private final Airport departure;
    private final Airport arrival;
    private final Airline airline;
    private final Plane plane;
    // Time and Date kept in UTC
    private final LocalDateTime departureTime;
    private final LocalDateTime arrivalTime;
    // Stops

    Flight(String code, int number, Airport departure, Airport arrival, Plane plane, Airline airline,
            LocalDateTime departureTime,
            LocalDateTime arrivalTime) {
        this.code = code;
        this.number = number;
        this.departure = departure;
        this.arrival = arrival;
        this.plane = plane;
        this.airline = airline;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public String getCode() {
        return code;
    }

    public Airline getAirline() {
        return airline;
    }

    public int getNumber() {
        return number;
    }

    public Airport getDepartureAirport() {
        return departure;
    }

    public Airport getArrivalAirport() {
        return arrival;
    }

    public Plane getPlane() {
        return plane;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public long getDuration() {
        return Duration.between(departureTime, arrivalTime).toMinutes();
    }

    // Use Haversine formula to calculate distance between two airports
    public static double getDistance(Airport departure, Airport arrival) {
        double lat1 = departure.getLatitude();
        double lon1 = departure.getLongitude();
        double lat2 = arrival.getLatitude();
        double lon2 = arrival.getLongitude();

        // The math module contains a function named toRadians which converts from
        // degrees to radians.
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        // Radius of earth in kilometers. Use 3956 for miles
        double r = 6371;

        // calculate the result
        return (c * r);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "\ncode='" + code + '\'' +
                ", \nnumber=" + number +
                ", \ndeparture=" + departure +
                ", \narrival=" + arrival +
                ", \nplane=" + plane +
                ", \ndepartureTime=" + departureTime +
                ", \narrivalTime=" + arrivalTime +
                '}';
    }
}
