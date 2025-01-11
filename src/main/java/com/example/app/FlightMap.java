package com.example.app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class FlightMap {
    HashMap<String, Flight> flightMap;
    HashMap<String, ArrayList<Flight>> departureAirportMap;

    FlightMap() {
        flightMap = new HashMap<>();
        departureAirportMap = new HashMap<>();
    }

    public void addFlight(Flight flight) {
        flightMap.put(flight.getCode(), flight);
        ArrayList<Flight> departure = departureAirportMap.get(flight.getDepartureAirport().getCode());
        if (departure != null) {
            departure.add(flight);
        } else {
            departure = new ArrayList<>();
            departure.add(flight);
            departureAirportMap.put(flight.getDepartureAirport().getCode(), departure);
        }
    }

    public Flight getFlight(String code) {
        return flightMap.get(code);
    }

    public void removeFlight(String code) {
        flightMap.remove(code);
    }

    @Override
    public String toString() {
        return "FlightMap{" + "flightMap=" + flightMap + '}';
    }

    // Using an ArrayList<> that contains an inner ArrayList of Flights that
    // represent the path from Airport a to Airport b
    public ArrayList<ArrayList<Flight>> getFlightsFromAToB(String departureAirportCode, String arrivalAirportCode,
            LocalDate date) {
        ArrayList<ArrayList<Flight>> pathList = new ArrayList<>();
        for (Flight flight : departureAirportMap.get(departureAirportCode)) {
            if (flight.getPlane().getAvailableSeats() > 0) {
                if (flight.getArrivalAirport().getCode().equals(arrivalAirportCode)
                        && flight.getDepartureTime().isBefore(date.atTime(23, 59))) {
                    ArrayList<Flight> path = new ArrayList<>();
                    path.add(flight);
                    pathList.add(path);
                } else if (flight.getDepartureTime().isBefore(date.atTime(23, 59))) {
                    for (Flight nextFlight : departureAirportMap.get(flight.getArrivalAirport().getCode())) {
                        // Checking if the next flight has b as its destination airport and if the next
                        // flight departs 30 minutes after the current flight arrives
                        if (nextFlight.getArrivalAirport().getCode().equals(arrivalAirportCode)
                                && flight.getArrivalTime().plusMinutes(30).isBefore(nextFlight.getDepartureTime())
                                && nextFlight.getPlane().getAvailableSeats() > 0
                                && nextFlight.getDepartureTime().isBefore(flight.getDepartureTime().plusDays(1))) {
                            ArrayList<Flight> path = new ArrayList<>();
                            path.add(flight);
                            path.add(nextFlight);
                            pathList.add(path);
                        }
                    }
                }
            }
        }
        sort(pathList);
        return pathList;
    }

    public static void sort(ArrayList<ArrayList<Flight>> flightList) {
        quickSort(flightList, 0, flightList.size() - 1);

    }

    public static int averageOfConnectingFlights(ArrayList<Flight> flights) {
        float sum = 0;
        float seats = 0;
        for (int i = 0; i < flights.size(); i++) {
            sum += flights.get(i).getPlane().getAvailableSeats();
            seats += flights.get(i).getPlane().getSeats().length;
        }
        return (int) ((sum / seats) * 100);
    }

    // Quick Sorting algorithm implemented in the labs
    private static void quickSort(ArrayList<ArrayList<Flight>> theArray, int low, int high) {
        int pivot_index;
        if (low < high) {
            pivot_index = partition(theArray, low, high);
            quickSort(theArray, low, pivot_index - 1);
            quickSort(theArray, pivot_index + 1, high);
        }
    }

    private static int partition(ArrayList<ArrayList<Flight>> theArray, int low, int high) {
        int left = low;
        int right = high;

        // Exercise 2b
        int pivot = averageOfConnectingFlights(theArray.get(low));
        // int pivot = median3(theArray, low, high);
        while (left < right) {
            while ((left < high) && (averageOfConnectingFlights(theArray.get(left)) <= pivot)) {
                left++;
            }

            while (averageOfConnectingFlights(theArray.get(right)) > pivot) {
                right--;
            }

            if (left < right) {
                swapValues(theArray, left, right);
            }
        }

        swapValues(theArray, low, right);
        return right;
    }

    private static void swapValues(ArrayList<ArrayList<Flight>> a, int index1, int index2) {
        Collections.swap(a, index1, index2);
        System.out.println("Swapping " + index1 + " with " + index2);
    }
}