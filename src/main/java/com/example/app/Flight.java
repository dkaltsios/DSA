package com.example.app;

import java.time.Duration;
import java.time.LocalDateTime;

public class Flight {
    private final String code;
    // TODO: WE NEED TO ADD BASE SALES PRICE FOR FLIGHT
    private final int number;
    private final Airport departure;
    private final Airport arrival;
    private final Plane plane;
    // Time and Date kept in UTC
    private final LocalDateTime departureTime;
    private final LocalDateTime arrivalTime;

    Flight(String code, int number, Airport departure, Airport arrival, Plane plane, LocalDateTime departureTime,
            LocalDateTime arrivalTime) {
        this.code = code;
        this.number = number;
        this.departure = departure;
        this.arrival = arrival;
        this.plane = plane;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public String getCode() {
        return code;
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

    // dynamicflightpricing() method, use plane.getAvailableSeats()
}
