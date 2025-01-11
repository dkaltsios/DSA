package com.example.app;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    static FlightMap flightMap;
    static HashMap<String, Plane> planes;
    static HashMap<String, Airport> airports;
    static HashMap<String, Airline> airlines;
    static boolean isRunning = true;

    public static void main(String[] args) {

        // Airport database
        String airportFile = "src/main/resources/airports.csv";
        // Load airports into a hashmap
        airports = AirportLoader.loadAirports(airportFile);
        // Load airlines into a hashmap
        String airlineFile = "src/main/resources/airlines.csv";
        airlines = AirlineLoader.loadAirlines(airlineFile);
        // Load planes into a hashmap
        String planeFile = "src/main/resources/planes.csv";
        planes = PlaneLoader.loadPlanes(planeFile);
        // Load flights into a FlightMap
        String flightFile = "src/main/resources/flights_medium.csv";
        flightMap = FlightLoader.loadFlights(flightFile, airlines, planes, airports);
        do {
            menu();
        } while (isRunning);
    }

    private static void menu() {

        // User input
        String destination;
        String origin;
        LocalDate date;
        // Get destination and origin airports
        try (Scanner scanner = new Scanner(System.in)) {
            origin = inputAirport(airports, "Enter origin airport (EXIT to quit): ",
                    scanner);
            destination = inputAirport(airports, "Enter destination airport (EXIT to quit):",
                    scanner);
            date = inputDate("Enter date (YYYY-MM-DD) (EXIT to quit): ", scanner);
        }

        // Return a list of flights from origin to destination
        ArrayList<ArrayList<Flight>> flightList = flightMap.getFlightsFromAToB(origin, destination,
                date);
        if (flightList.size() == 0) {
            System.out.println("No flights available");
        } else {
            for (ArrayList<Flight> path : flightList) {
                printPath(path);
                System.out.println("Price: " + (int) Flight.getPrice(path));
                System.out.println("Departure time: "
                        + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(path.get(0).getDepartureTime()));
                System.out.println("Arrival time: " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                        .format(path.get(path.size() - 1).getArrivalTime()));
                System.out.println();
            }
        }
    }

    private static void printPath(ArrayList<Flight> path) {
        System.out.print(path.get(0).getDepartureAirport().getCode() + " -> ");
        for (int i = 0; i < path.size() - 1; i++) {
            System.out.print(path.get(i).getArrivalAirport().getCode() + " -> ");
        }
        System.out.print(path.get(path.size() - 1).getArrivalAirport().getCode() + "\n");
    }

    private static String inputAirport(HashMap<String, Airport> airports, String message, Scanner scanner) {
        System.out.print(message);
        String airport;
        do {
            airport = scanner.nextLine();
            if (airport.equals("EXIT")) {
                isRunning = false;
                System.exit(0);
            }
            if (!airports.containsKey(airport)) {
                System.out.print("Invalid airport. Please try again (EXIT to quit): ");
            }
        } while (!airports.containsKey(airport));
        return airport;
    }

    private static LocalDate inputDate(String message, Scanner scanner) {
        System.out.print(message);
        String date;
        boolean validDate = false;
        do {
            date = scanner.nextLine();
            if (date.equals("EXIT")) {
                isRunning = false;
                System.exit(0);
            }
            try {
                LocalDate.parse(date);
                validDate = true;
            } catch (Exception e) {
                System.out.print("Invalid date. Please try again (YYYY-MM-DD) (EXIT to quit): ");
            }
        } while (!validDate);
        return LocalDate.parse(date);
    }
}