package com.example.app;

public class ReservationMap {
    HashMap<String, Reservation> reservationMap;

    public ReservationMap() {
        reservationMap = new HashMap<>();
    }

    public void add(Reservation reservation) {
        reservationMap.put(reservation.getCode(), reservation);
    }

    public Reservation get(String reservationId) {
        return reservationMap.get(reservationId);
    }

    public void remove(String reservationId) {
        reservationMap.remove(reservationId);
    }
}
