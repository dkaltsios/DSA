package com.example.app;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        // Airport database
        String airportFile = "src/main/resources/airports.csv";
        // Load airports into a hashmap
        HashMap<String, Airport> airports = AirportLoader.loadAirports(airportFile);
        // Load airlines into a hashmap
        String airlineFile = "src/main/resources/airlines.csv";
        HashMap<String, Airline> airlines = AirlineLoader.loadAirlines(airlineFile);
        // Load planes into a hashmap
        String planeFile = "src/main/resources/planes.csv";
        HashMap<String, Plane> planes = PlaneLoader.loadPlanes(planeFile);
        // Load flights into a FlightMap
        String flightFile = "src/main/resources/flights.csv";
        FlightMap flightMap = FlightLoader.loadFlights(flightFile, airlines, planes, airports);

        // User input
        Airport destination;
        Airport origin;
        // Get destination and origin airports
        try (Scanner scanner = new Scanner(System.in)) {
            String destination_code = inputAirport(airports, "Enter destination airport:",
                    scanner);
            destination = airports.get(destination_code);
            String origin_code = inputAirport(airports, "Enter origin airport: ",
                    scanner);
            origin = airports.get(origin_code);
        }

        // Return a list of flights from origin to destination

        // Get the earliest flight
    }

    private static String inputAirport(HashMap<String, Airport> airports, String message, Scanner scanner) {
        System.out.print(message);
        String airport;
        do {
            airport = scanner.nextLine();
            if (!airports.containsKey(airport)) {
                System.out.print("Invalid airport. Please try again: ");
            }
        } while (!airports.containsKey(airport));
        return airport;
    }

    private static Airline inputAirline(HashMap<String, Airline> airlines, String message, Scanner scanner) {
        System.out.print(message);
        String airline;
        do {
            airline = scanner.nextLine();
            if (!airlines.containsKey(airline)) {
                System.out.print("Invalid airline. Please try again: ");
            }
        } while (!airlines.containsKey(airline));
        return airlines.get(airline);
    }
}