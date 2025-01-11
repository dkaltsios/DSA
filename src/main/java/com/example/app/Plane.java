package com.example.app;

import java.util.Random;

public class Plane {
    private final String model;
    private final String manufacturer;
    private Seat[] seats;
    private final Random random = new Random();

    // Using enum since the seat letters are fixed
    private enum SeatLetter {
        A, B, C, D, E, F
    }

    public Plane(String model, String manufacturer, int numberOfSeats) {
        this.model = model;
        this.manufacturer = manufacturer;
        setSeats(numberOfSeats);
        makeBooked();
    }

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Seat[] getSeats() {
        return seats;
    }

    public void makeBooked() {
        int numberOfSeats = random.nextInt(this.seats.length);
        for (int i = 0; i < numberOfSeats; i++) {
            this.seats[i].setBooked(true);
        }
    }

    public int getAvailableSeats() {
        int available = 0;
        for (Seat seat : seats) {
            if (!seat.isBooked()) {
                available++;
            }
        }
        return available;
    }

    private void setSeats(int capacity) {
        this.seats = new Seat[capacity];
        String seatcode;
        for (int i = 0; i < capacity / 6; i++) {
            for (int j = 0; j < 6; j++) {
                seatcode = (i + 1 + "" + SeatLetter.values()[j]);
                this.seats[i * 6 + j] = new Seat(seatcode);
            }

        }
    }

    public void setBooked(String seatCode) {
        int seatIndex = Integer.parseInt(seatCode.substring(0, seatCode.length() - 1));
        char seatRow = seatCode.charAt(seatCode.length() - 1);
        int seatRowInt = seatRow - 'A';
        int index = (seatIndex - 1) * 6 + seatRowInt;
        this.seats[index].setBooked(true);
    }

    @Override
    public String toString() {
        return "Plane{" +
                "\nmanufacturer='" + manufacturer + '\'' +
                ", \nmodel='" + model + '\'' +
                ", \navailable seats=" + getAvailableSeats() +
                '}';
    }
}