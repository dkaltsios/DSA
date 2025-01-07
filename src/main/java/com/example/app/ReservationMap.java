package com.example.app;

public class ReservationMap {
    HashMap<String, Reservation> reservationMap;

    public ReservationMap() {
        reservationMap = new HashMap<>();
    }
    
    public void addReservation(Reservation reservation) {
        reservationMap.put(reservation.getCode(), reservation);
    }
    
    public Reservation getReservation(String reservationId) {
        return reservationMap.get(reservationId);
    }
    
    public void removeReservation(String reservationId) {
        reservationMap.remove(reservationId);
    }
}
