package com.example.app;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class FlightLoader {
    // The date range of departure for the flight would be form the current time to
    // the next 2 weeks
    // The arrival time would be the departure time plus the distance divided by the
    // speed of the plane

    // TODO: Write a script for the flight number to be generated from 1000-9999 but
    // be unique to the airline and route
    public static FlightMap loadFlights(String file, HashMap<String, Airline> airlines,
            HashMap<String, Plane> planes, HashMap<String, Airport> airports) {
        FlightMap flights = new FlightMap();
        Random rand = new Random();
        // Read and load airlines from file
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            // Skip the first line
            reader.readNext();
            String[] parts;
            while ((parts = reader.readNext()) != null) {
                // Airline,Plane,Number,IATA,Departure,Arrival
                Airline airline = airlines.get(parts[0]);
                Plane plane = planes.get(parts[1]);
                int number = Integer.parseInt(parts[2]);
                String code = parts[3];
                Airport departure = airports.get(parts[4]);
                Airport arrival = airports.get(parts[5]);
                LocalDateTime departureTime = LocalDateTime.now();
                double distance = Flight.getDistance(departure, arrival);
                int duration = (int) (distance / 880 * 60);
                LocalDateTime arrivalTime = departureTime.plusMinutes(duration);

                flights.addFlight(
                        new Flight(code, number, departure, arrival, plane, airline, departureTime, arrivalTime));
            }
        } catch (IOException | CsvValidationException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return flights;
    }
}
