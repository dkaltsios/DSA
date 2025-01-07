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
        // Flights
        FlightMap flightMap = new FlightMap();

        Airport destination;
        Airport origin;
        try ( // Get destination and origin airports
                Scanner scanner = new Scanner(System.in)) {
            String destination_code = inputAirport(airports, "Enter destination airport: ", scanner);
            destination = airports.get(destination_code);
            String origin_code = inputAirport(airports, "Enter origin airport: ", scanner);
            origin = airports.get(origin_code);
        }
        Plane plane = new Plane("Boeing 737", "1", new Airline("AA", "American Airlines"), 120);
        Flight flight = new Flight(
                "AA",
                123,
                origin,
                destination,
                plane,
                LocalDateTime.now(),
                LocalDateTime.now().plus(Duration.ofHours(2)), 0,
                "1A");
        flightMap.addFlight(flight);

        double price = flight.getPrice();
        System.out.println("Price: " + price);
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
}