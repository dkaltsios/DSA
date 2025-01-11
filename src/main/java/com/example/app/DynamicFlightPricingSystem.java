package com.example.app;

import java.util.ArrayList;
import java.util.Collections;

public class DynamicFlightPricingSystem {
    public static void main(String[] args) {
        Plane p1 = new Plane( "B747","Boeing 747", new Airline(null, null), 60);
        Plane p2 = new Plane( "A747","Kaltsios 747", new Airline(null, null), 60);
        Plane p3 = new Plane( "YY747","Sudak 747", new Airline(null, null), 60);
        Plane p4 = new Plane( "C747","Chatzo 747", new Airline(null, null), 60);
        p1.setBooked("1A");
        p1.setBooked("1B");
        p1.setBooked("1C");
        p1.setBooked("1D");
        p1.setBooked("1E");
        p3.setBooked("1D");
        p4.setBooked("1E");
        p4.setBooked("1F");
        Flight f1 = new Flight("F1", 1, null, null, p1, null, null);
        Flight f2 = new Flight("F2", 2, null, null, p2, null, null);
        Flight f3 = new Flight("F3", 3, null, null, p3, null, null);
        Flight f4 = new Flight("F4", 4, null, null, p4, null, null);
        ArrayList<ArrayList<Flight>> flightList = new ArrayList<ArrayList<Flight>>();
        ArrayList<Flight> flightList1 = new ArrayList<Flight>();
        ArrayList<Flight> flightList2 = new ArrayList<Flight>();
        ArrayList<Flight> flightList3 = new ArrayList<Flight>();
        ArrayList<Flight> flightList4 = new ArrayList<Flight>();

        flightList1.add(f1);
        flightList2.add(f2);
        flightList3.add(f3);
        flightList4.add(f4);
        flightList.add(flightList3);
        flightList.add(flightList1);
        flightList.add(flightList2);
        flightList.add(flightList4);
        printNumberOfSeats(flightList);
        sort(flightList);
        printNumberOfSeats(flightList);
    }

    public static void printNumberOfSeats(ArrayList<ArrayList<Flight>> flightList) {
        for (int i = 0; i < flightList.size(); i++) {
            for (int j = 0; j < flightList.get(i).size(); j++) {
                System.out.println(flightList.get(i).get(j).getPlane().getAvailableSeats());
            }
        }
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